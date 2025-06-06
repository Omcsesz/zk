/* datefmt.ts

	Purpose:

	Description:

	History:
		Fri Jan 16 19:13:43	 2009, Created by Flyworld

Copyright (C) 2008 Potix Corporation. All Rights Reserved.

This program is distributed under LGPL Version 2.1 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
*/
function _parseTextToArray(txt: string, fmt: string): string[] | undefined {
	var ts: string[] = [],
		mindex = fmt.indexOf('MMM'),
		eindex = fmt.indexOf('E'),
		fmtlen = fmt.length,
		ary: string[] = [],
		aa = fmt.indexOf('a'),
		gg = fmt.indexOf('G'),
		tlen = txt.replace(/[^.]/g, '').length,
		flen = fmt.replace(/[^.]/g, '').length,
		literalIndexes: number[] = extractLiteralIndexesIfAny(fmt), // [startIndex1, endIndex1, startIndex2, endIndex2, ...]
		literalTotalCount = literalIndexes.length / 2,
		nextLiteralStartIndex = literalTotalCount > 0 ? literalIndexes[0] : -1,
		nextLiteralEndIndex = literalTotalCount > 0 ? literalIndexes[1] : -1,
		literalCount = 0,
		isLiteral = false;

	for (var i = 0, k = 0, j = txt.length; k < j; i++, k++) {
		var c = txt.charAt(k),
			f = fmtlen > i ? fmt.charAt(i) : '';
		if (f == '\'') { // skip single quote
			if (!isLiteral && c != fmt.charAt(nextLiteralStartIndex + 1) && i == nextLiteralStartIndex) { //enter literal
				i--;
			} else {
				isLiteral = !isLiteral;
				k--;
				if (!isLiteral && i == nextLiteralEndIndex) {
					literalCount++;
					if (literalCount < literalTotalCount) {
						nextLiteralStartIndex = literalIndexes[literalCount * 2];
						nextLiteralEndIndex = literalIndexes[literalCount * 2 + 1];
					}
				}
				continue;
			}
		}
		if (c.match(/\d/)) {
			ary.push(c);
		} else if (!isLiteral && ((mindex >= 0 && mindex <= i /*&& mmindex >= i location French will lose last char */)
			|| (eindex >= 0 && eindex <= i) || (aa > -1 && aa <= i) || (gg > -1 && gg <= i))) {
			if (c.match(/\w/)) {
				ary.push(c);
			} else {
				if (c.charCodeAt(0) < 128 && (c.charCodeAt(0) != 46
					|| tlen == flen || f.charCodeAt(0) == 46)) {
					if (ary.length) {
						ts.push(ary.join(''));
						ary = [];
					}
				} else
					ary.push(c);
			}
		} else if (ary.length) {
			if (txt.charAt(k - 1).match(/\d/))
				while (f == fmt.charAt(i - 1) && f) {
					f = fmt.charAt(++i);
				}
			ts.push(ary.join(''));
			ary = [];
		} else if (c.match(/\w/)) {
			if (isLiteral) {
				continue;
			}
			return; //failed
		}
	}
	if (ary.length) ts.push(ary.join(''));
	return ts;
}
function _parseToken(token: string, ts: string[], i: number, len: number): string {
	if (len < 2) len = 2;
	if (token && token.length > len) {
		ts[i] = token.substring(len);
		return token.substring(0, len);
	}
	return token;
}
function _parseInt(v: string): number {
	return parseInt(v, 10);
}
function extractLiteralIndexesIfAny(str: string): number[] {
	let pattern = /'([^']*?)'/g,
		matchesIndexes: number[] = [],
		match: RegExpExecArray | unknown;
	while ((match = pattern.exec(str)) != undefined && (match as RegExpExecArray)[1].length > 0) {
		let index = (match as RegExpExecArray).index;
		matchesIndexes.push(index);
		matchesIndexes.push(index + (match as RegExpExecArray)[0].length - 1);
	}
	return matchesIndexes;
}
function _digitFixed(val: string | number, digits?: number): string {
	var s = String(val);
	for (var j = digits! - s.length; --j >= 0;)
		s = '0' + s;
	return s;
}
function _dayInYear(d: DateImpl, ref: DateImpl): number {
	return Math.round((+Dates.newInstance([d.getFullYear(), d.getMonth(), d.getDate()], d.getTimeZone()) - +ref) / 864e5);
}
// Converts milli-second to day.
//	function _ms2day(t) {
//		return Math.round(t / 86400000);
//	}
// Day in year (starting at 1).
function dayInYear(d: DateImpl): string {
	var ref = Dates.newInstance([d.getFullYear(), 0, 1], d.getTimeZone());
	return _digitFixed(1 + _dayInYear(d, ref));
}
//Day in month (starting at 1).
function dayInMonth(d: DateImpl): number {
	return d.getDate();
}
//Week in month (starting at 1).
function weekInMonth(d: DateImpl, firstDayOfWeek: number): string {
	var ref = Dates.newInstance([d.getFullYear(), d.getMonth(), 1], d.getTimeZone()),
		day = ref.getDay(),
		shift = (firstDayOfWeek > day ? day + 7 : day) - firstDayOfWeek;
	return _digitFixed(1 + Math.floor((_dayInYear(d, ref) + shift) / 7));
}
//Day of week in month.
function dayOfWeekInMonth(d: DateImpl): string {
	var ref = Dates.newInstance([d.getFullYear(), d.getMonth(), 1], d.getTimeZone());
	return _digitFixed(1 + Math.floor(_dayInYear(d, ref) / 7));
}

// a proxy of Date object for leap day on Thai locale - B60-ZK-1010
@zk.WrapClass('zul.fmt.LeapDay')
class LeapDay extends zk.Object {
	/** @internal */
	_date: DateImpl;
	/** @internal */
	_offset?: number;

	constructor(date: DateImpl)
	constructor(y: number, m: number, d: number, hr: number, min: number, sec: number, msec: number, tz?: string)
	constructor(y: DateImpl | number, m?, d?, hr?, min?, sec?, msec?, tz?: string) {
		super();
		if (arguments.length > 1) {
			this._date = Dates.newInstance([y as number, m, d, hr, min, sec, msec], tz);
		} else
			this._date = y as DateImpl;
	}
	// eslint-disable-next-line zk/javaStyleSetterSignature
	setOffset(offset: number): void {
		this._offset = offset;
	}
	// eslint-disable-next-line zk/javaStyleSetterSignature
	setFullYear(fullYear: number): void {
		// no need to subtract the._offset, the caller will handle
		this._date.setFullYear(fullYear);
	}
	getFullYear(): number {
		return this._date.getFullYear() + (this._offset || 0);
	}
	getDate(): number {
		return this._date.getDate();
	}
	// eslint-disable-next-line zk/javaStyleSetterSignature
	setDate(date: number): void {
		this._date.setDate(date);
	}
	getDay(): number {
		return this._date.getDay();
	}
	getMonth(): number {
		return this._date.getMonth();
	}
	// eslint-disable-next-line zk/javaStyleSetterSignature
	setMonth(month: number): void {
		this._date.setMonth(month);
	}
	getHours(): number {
		return this._date.getHours();
	}
	// eslint-disable-next-line zk/javaStyleSetterSignature
	setHours(hours: number): void {
		this._date.setHours(hours);
	}
	getMinutes(): number {
		return this._date.getMinutes();
	}
	// eslint-disable-next-line zk/javaStyleSetterSignature
	setMinutes(minutes: number): void {
		this._date.setMinutes(minutes);
	}
	getSeconds(): number {
		return this._date.getSeconds();
	}
	// eslint-disable-next-line zk/javaStyleSetterSignature
	setSeconds(seconds: number): void {
		this._date.setSeconds(seconds);
	}
	getMilliseconds(): number {
		return this._date.getMilliseconds();
	}
	// eslint-disable-next-line zk/javaStyleSetterSignature
	setMilliseconds(milliseconds: number): void {
		this._date.setMilliseconds(milliseconds);
	}
	getTimezoneOffset(): number {
		return this._date.getTimezoneOffset();
	}
	getRealDate(): DateImpl {
		return this._date;
	}
	getTimeZone(): string {
		return this._date.getTimeZone();
	}
}
var DateFmt = {
	_isoDateTimeFormat: new Intl.DateTimeFormat('en-US', { year: 'numeric' }),
	// strictDate: No invalid date allowed (e.g., Jan 0 or Nov 31)
	// nonLenient: strictDate + inputs must match this object's format (no additional character allowed)
	parseDate(
		txt: string,
		fmt: string,
		nonLenient?: boolean,
		refval?: DateImpl,
		localizedSymbols?: zk.LocalizedSymbols,
		tz?: string,
		strictDate?: boolean
	): DateImpl | undefined {
		if (!fmt) fmt = 'yyyy/MM/dd';
		refval ??= zUtl.today(fmt, tz);

		localizedSymbols ??= {
			DOW_1ST: zk.DOW_1ST,
			MINDAYS: zk.MINDAYS,
			ERA: zk.ERA,
			YDELTA: zk.YDELTA,
			LAN_TAG: zk.LAN_TAG,
			SDOW: zk.SDOW,
			S2DOW: zk.S2DOW,
			FDOW: zk.FDOW,
			SMON: zk.SMON,
			S2MON: zk.S2MON,
			FMON: zk.FMON,
			APM: zk.APM
		};
		var y = refval.getFullYear(),
			m = refval.getMonth(),
			d = refval.getDate(), dFound,
			hr = refval.getHours(),
			min = refval.getMinutes(),
			sec = refval.getSeconds(),
			msec = refval.getMilliseconds(),
			aa = fmt.indexOf('a'),
			hasAM = aa > -1,
			hasG = fmt.includes('G'),
			hasHour1 = hasAM && (fmt.includes('h') || fmt.includes('K')),
			isAM,
			ts = _parseTextToArray(txt, fmt),
			regexp = /^\d+$/,
			// ZK-2026: Don't use isNaN(), it will treat float as number.
			isNumber = regexp.test(txt),
			eras = localizedSymbols.ERAS,
			era: zk.LocalizedSymbols.ErasElementType | undefined,
			eraKey: string | undefined;

		if (hasG && txt && eras) { // ZK-4745: parsing era for specific calendar system
			eraKey = this._findEraKey(txt, eras);
			if (eraKey)
				era = eras[eraKey];
		}

		fmt = fmt.replace(/'.*?'/g, ' '); //ZK-5475 remove any string enclosed by single quotes

		var refDate = refval._moment.toDate(),
			localeDateTimeFormat = new Intl.DateTimeFormat(localizedSymbols.LAN_TAG, { year: 'numeric' }),
			eraName = localizedSymbols.ERA || (eraKey ? eraKey : this.getEraName(refDate, localizedSymbols, localeDateTimeFormat)),
			ydelta = localizedSymbols.YDELTA || (era ? (0 - era.firstYear + era.direction * 1) : this.getYDelta(refDate, localeDateTimeFormat));

		if (!ts || !ts.length) return;
		for (var i = 0, j = 0, offs = 0, fl = fmt.length; j < fl; ++j) {
			var cc = fmt.charAt(j);
			if ((cc >= 'a' && cc <= 'z') || (cc >= 'A' && cc <= 'Z')) {
				var len = 1, k: number;
				for (k = j; ++k < fl; ++len)
					if (fmt.charAt(k) != cc)
						break;

				var nosep, nv: number; //no separator
				if (k < fl) {
					var c2 = fmt.charAt(k);
					nosep = 'yuMdEmsShHkKaAG'.includes(c2);
				}
				var token = isNumber ? ts[0].substring(j - offs, k - offs) : ts[i++];
				switch (cc) {
					case 'u':
					case 'y':
						// ZK-1985: Determine if token's length is less than the expected when nonLenient is true.
						if (nonLenient && token && (token.length < len))
							return;

						if (nosep) {
							if (len < 3) len = 2;
							if (token && token.length > len) {
								ts[--i] = token.substring(len);
								token = token.substring(0, len);
							}
						}

						// ZK-1985:	Determine if token contains non-digital word when nonLenient is true.
						if (nonLenient && token && !regexp.test(token))
							return;

						if (!isNaN(nv = _parseInt(token))) {
							var newY = Math.min(nv, 200000); // Bug B50-3288904: js year limit
							if (newY < 100 && newY === (y + ydelta) % 100) break; // assume yy is not modified
							if (ydelta === 0 && newY < 100) { // only handle twoDigitYearStart with ISO calendar for now
								// ZK-4235: Datefmt parseDate always return date between 1930-2029 when using yy format
								var twoDigitYearStart = zk.TDYS,
									lowerBoundary = (Math.floor(twoDigitYearStart / 100) * 100) + newY,
									upperBoundary = lowerBoundary + 100;
								y = lowerBoundary > twoDigitYearStart ? lowerBoundary : upperBoundary;
							} else {
								if (cc == 'y') {
									if (era)
										newY = era.firstYear + era.direction * (newY - 1);
									else
										newY = (y + ydelta > 0) ? newY - ydelta : 1 - newY - ydelta;
								}
								y = newY;
							}
						}
						break;
					case 'M':
						var mon = token ? token.toLowerCase() : '',
							isNumber0 = !isNaN(token as unknown as number) || len <= 2; // could be MM with nosep token
						if (!mon) break;
						if (!isNumber0 && token) {
							// MMM or MMMM
							var symbols: string[];
							if (len == 3)
								symbols = localizedSymbols.SMON!;
							else if (len == 4)
								symbols = localizedSymbols.FMON!;
							else
								break;

							for (var index = symbols.length, brkswch; --index >= 0;) {
								var monStr = symbols[index].toLowerCase();

								if ((nonLenient && mon == monStr) || (!nonLenient && mon.startsWith(monStr))) {
									var monStrLen = monStr.length;

									if (token && token.length > monStrLen)
										ts[--i] = token.substring(monStrLen);

									m = index;
									brkswch = true;
									break; // shall break to switch level: B50-3314513
								}
							}
							if (brkswch)
								break;
						}
						if (len == 3 && token) {
							if (nosep)
								token = _parseToken(token, ts, --i, token.length);//token.length: the length of French month is 4
							if (isNaN(nv = _parseInt(token)))
								return; // failed, B50-3314513
							m = nv - 1;
						} else if (len <= 2) {
							if (nosep && token && token.length > 2) {//Bug 2560497 : if no separator, token must be assigned.
								ts[--i] = token.substring(2);
								token = token.substring(0, 2);
							}
							if (isNaN(nv = _parseInt(token)))
								return; // failed, B50-3314513
							m = nv - 1;
						} else {
							for (var l = 0; ; ++l) {
								if (l == 12) return; //failed
								if (len == 3) {
									if (localizedSymbols.SMON![l] == token) {
										m = l;
										break;
									}
								} else {
									if (token && localizedSymbols.FMON![l].startsWith(token)) {
										m = l;
										break;
									}
								}
							}
						}
						if (m > 11 /*|| m < 0 accept 0 since it is a common-yet-acceptable error*/) //restrict since user might input year for month
							return;//failed
						break;
					case 'E':
						if (nosep)
							_parseToken(token, ts, --i, len);
						break;
					case 'd':
						// ZK-1985:	Determine if token's length is less than expected when nonLenient is true.
						if (nonLenient && token && (token.length < len))
							return;

						if (nosep)
							token = _parseToken(token, ts, --i, len);

						// ZK-1985:	Determine if token contains non-digital word when nonLenient is true.
						if (nonLenient && token && !regexp.test(token))
							return;

						if (!isNaN(nv = _parseInt(token))) {
							d = nv;
							dFound = true;
							if (d < 0 || d > 31) //restrict since user might input year for day (ok to allow 0 and 31 for easy entry)
								return; //failed
						}
						break;
					case 'H':
					case 'h':
					case 'K':
					case 'k':
						// ZK-1985:	Determine if token's length is less than the expected when nonLenient is true.
						if (nonLenient && token && (token.length < len))
							return;

						if (hasHour1 ? (cc == 'H' || cc == 'k') : (cc == 'h' || cc == 'K'))
							break;
						if (nosep)
							token = _parseToken(token, ts, --i, len);

						// ZK-1985:	Determine if token contains non-digital word when nonLenient is true.
						if (nonLenient && token && !regexp.test(token))
							return;

						if (!isNaN(nv = _parseInt(token)))
							hr = (cc == 'h' && nv == 12) || (cc == 'k' && nv == 24) ?
								0 : cc == 'K' ? nv % 12 : nv;
						break;
					case 'm':
					case 's':
					case 'S':
						// ZK-1985:	Determine if token's length is less than the expected when nonLenient is true.
						if (nonLenient && token && (token.length < len))
							return;

						if (nosep)
							token = _parseToken(token, ts, --i, len);

						// ZK-1985:	Determine if token contains non-digital word when nonLenient is true.
						if (nonLenient && token && !regexp.test(token))
							return;

						if (!isNaN(nv = _parseInt(token))) {
							if (cc == 'm') min = nv;
							else if (cc == 's') sec = nv;
							else msec = nv;
						}
						break;
					case 'a':
						if (!hasHour1)
							break;
						if (nosep)
							token = _parseToken(token, ts, --i, len);
						if (!token) return; //failed
						var tokenUpperCase = token.toUpperCase(),
							apmText = localizedSymbols.APM![0].toUpperCase();
						if (tokenUpperCase.startsWith(apmText)) {
							isAM = true;
						} else if (apmText.startsWith(tokenUpperCase) && ts[i] && apmText.endsWith(ts[i].toUpperCase())) { // ZK-5216, Spanish AM/PM
							isAM = true;
							i++;
						} else {
							isAM = false;
						}
						break;
					case 'G':
						if (nosep && eras) {
							if (!eraName) return; // no era match
							token = _parseToken(token, ts, --i, eraName.length);
						}
						if (eraName && token != eraName && eraName.length > token.length) { // there is space in eraName
							token = eraName;
							i += eraName.match(/([\s]+)/g)!.length;
						}
						break;
					//default: ignored
				}
				j = k - 1;
			} else offs++;
		}

		if (hasHour1 && isAM === false)
			hr += 12;
		var dt: DateImpl | LeapDay; // TODO: enforce common interface?
		if (m == 1 && d == 29 && ydelta) {
			dt = new LeapDay(y, m, d, hr, min, sec, msec, tz);
			dt.setOffset(ydelta);
		} else {
			dt = Dates.newInstance([y, m, d, hr, min, sec, msec], tz);
		}
		if (!dFound && dt.getMonth() != m)
			dt = Dates.newInstance([y, m + 1, 0, hr, min, sec, msec], tz); //last date of m
		if (nonLenient || strictDate)
			if (dt.getFullYear() != y || dt.getMonth() != m || dt.getDate() != d
				|| dt.getHours() != hr || dt.getMinutes() != min || dt.getSeconds() != sec) //ignore msec (safer though not accurate)
				return; //failed
		if (nonLenient) {
			txt = ts.reduce((txt, t) => txt.replace(t, ''), txt.trim());
			for (var k = txt.length; k--;) {
				var cc = txt.charAt(k);
				if ((cc > '9' || cc < '0') && !fmt.includes(cc))
					return; //failed
			}
		}
		return +dt == +refval ? refval : dt as DateImpl;
		//we have to use getTime() since dt == refVal always false
	},
	formatDate(val: DateImpl, fmt?: string, localizedSymbols?: zk.LocalizedSymbols): string {
		if (!fmt) fmt = 'yyyy/MM/dd';

		localizedSymbols ??= {
			DOW_1ST: zk.DOW_1ST,
			MINDAYS: zk.MINDAYS,
			ERA: zk.ERA,
			YDELTA: zk.YDELTA,
			LAN_TAG: zk.LAN_TAG,
			SDOW: zk.SDOW,
			S2DOW: zk.S2DOW,
			FDOW: zk.FDOW,
			SMON: zk.SMON,
			S2MON: zk.S2MON,
			FMON: zk.FMON,
			APM: zk.APM
		};
		var txt = '',
			localeDateTimeFormat = new Intl.DateTimeFormat(localizedSymbols.LAN_TAG, { year: 'numeric' }),
			singleQuote;
		for (var j = 0, fl = fmt.length; j < fl; ++j) {
			var cc = fmt.charAt(j);
			if ((cc >= 'a' && cc <= 'z') || (cc >= 'A' && cc <= 'Z')) {
				if (singleQuote) {
					txt += cc;
					continue;
				}
				var len = 1, k: number;
				for (k = j; ++k < fl; ++len)
					if (fmt.charAt(k) != cc) {
						if (fmt.charAt(k) == 'r') k++; // ZK-2964: for nl local uur
						break;
					}

				switch (cc) {
					case 'y':
					case 'u':
						var ydelta = 0;
						if (cc == 'y') ydelta = localizedSymbols.YDELTA || this.getYDelta(val._moment.toDate(), localeDateTimeFormat);
						var y = val.getFullYear() + ydelta;
						// ZK-4851: the year is truncated to 2 digits only if the number of pattern letters is 2
						if (len == 2) txt += _digitFixed(y % Math.pow(10, len), len);
						else txt += _digitFixed(y, len);
						break;
					case 'M':
						if (len <= 2) txt += _digitFixed(val.getMonth() + 1, len);
						else if (len == 3) txt += localizedSymbols.SMON![val.getMonth()];
						else txt += localizedSymbols.FMON![val.getMonth()];
						break;
					case 'd':
						txt += _digitFixed(dayInMonth(val), len);
						break;
					case 'E':
						if (len <= 3) txt += localizedSymbols.SDOW![(val.getDay() - localizedSymbols.DOW_1ST! + 7) % 7];
						else txt += localizedSymbols.FDOW![(val.getDay() - localizedSymbols.DOW_1ST! + 7) % 7];
						break;
					case 'D':
						txt += dayInYear(val);
						break;
					case 'w':
						txt += zUtl.getWeekOfYear(val.getFullYear(), val.getMonth(), val.getDate(), localizedSymbols.DOW_1ST!, localizedSymbols.MINDAYS!);
						break;
					case 'W':
						txt += weekInMonth(val, localizedSymbols.DOW_1ST!);
						break;
					case 'G':
						txt += localizedSymbols.ERA || this.getEraName(val._moment.toDate(), localizedSymbols, localeDateTimeFormat);
						break;
					case 'F':
						txt += dayOfWeekInMonth(val);
						break;
					case 'H':
						if (len <= 2) txt += _digitFixed(val.getHours(), len);
						break;
					case 'k':
						var h: number | string = val.getHours();
						if (h == 0)
							h = '24';
						if (len <= 2) txt += _digitFixed(h, len);
						break;
					case 'K':
						if (len <= 2) txt += _digitFixed(val.getHours() % 12, len);
						break;
					case 'h':
						var h: number | string = val.getHours();
						h %= 12;
						if (h == 0)
							h = '12';
						if (len <= 2) txt += _digitFixed(h, len);
						break;
					case 'm':
						if (len <= 2) txt += _digitFixed(val.getMinutes(), len);
						break;
					case 's':
						if (len <= 2) txt += _digitFixed(val.getSeconds(), len);
						break;
					case 'S':
						if (len <= 3) txt += _digitFixed(val.getMilliseconds(), len);
						break;
					case 'Z':
						txt += -(val.getTimezoneOffset() / 60);
						break;
					case 'z':
						txt += -(val.getTimezoneOffset() / 60);
						break;
					case 'a':
						txt += localizedSymbols.APM![val.getHours() > 11 ? 1 : 0];
						break;
					default:
						txt += '1';
					//fake; SimpleDateFormat.parse might ignore it
					//However, it must be an error if we don't generate a digit
				}
				j = k - 1;
			} else if (cc != "'") {
				txt += cc;
			} else if (cc === "'") {
				singleQuote = !singleQuote;
			}
		}
		return txt;
	},
	getYDelta(date?: Date, localeDateTimeFormat?: Intl.DateTimeFormat): number { // override
		return 0;
	},
	getEraName(date: Date, localizedSymbols: zk.LocalizedSymbols, localeDateTimeFormat?: Intl.DateTimeFormat): string { // override
		var langTag = localizedSymbols.LAN_TAG!.split('-').slice(0, 2).join('-'),
			localeDateString = date.toLocaleDateString(langTag, { era: 'short', day: 'numeric' });
		return localeDateString.split(' ')[0];
	},
	/** @internal */
	_findEraKey(txt: string, eras: NonNullable<zk.LocalizedSymbols['ERAS']>): string | undefined { // override
		return undefined;
	}
};
export { DateFmt as Date };
zk.fmt.Date = DateFmt;
/**
 * The `calendar` object provides a way
 * to convert between a specific instant in time for locale-sensitive
 * like buddhist's time.
 * <p>By default the year offset is specified from server if any.</p>
 * @since 5.0.1
 */
@zk.WrapClass('zk.fmt.Calendar')
export class Calendar extends zk.Object {
	/** @internal */
	_offset = zk.YDELTA;
	/** @internal */
	_date?: DateImpl;

	constructor(date?: DateImpl, localizedSymbols?: zk.LocalizedSymbols) {
		super();
		this._date = date;
		if (localizedSymbols) {
			const localeDateTimeFormat = new Intl.DateTimeFormat(localizedSymbols.LAN_TAG, {year: 'numeric'});
			this._offset = localizedSymbols.YDELTA || zk.fmt.Date.getYDelta(date ? date._moment.toDate() : undefined, localeDateTimeFormat);
		}
	}

	getTime(): DateImpl | undefined {
		return this._date;
	}

	setTime(time: DateImpl): this {
		this._date = time;
		return this;
	}

	setYearOffset(yearOffset: number): this {
		this._offset = yearOffset;
		return this;
	}

	getYearOffset(): number {
		return this._offset;
	}

	formatDate(val: DateImpl, fmt?: string, localizedSymbols?: zk.LocalizedSymbols): string {
		var d: LeapDay | undefined;
		if (localizedSymbols) {
			var localeDateTimeFormat = new Intl.DateTimeFormat(localizedSymbols.LAN_TAG, { year: 'numeric' });
			this._offset = localizedSymbols.YDELTA || zk.fmt.Date.getYDelta(val._moment.toDate(), localeDateTimeFormat);
		}

		if (this._offset) {
			if (val.getMonth() == 1 && val.getDate() == 29) {
				d = new LeapDay(val); // a proxy of Date
				d.setOffset(this._offset);
			}
		}
		return zk.fmt.Date.formatDate((d ?? val) as DateImpl, fmt, localizedSymbols);
	}

	toUTCDate(): DateImpl | undefined {
		if (this._date instanceof LeapDay)
			return this._date.getRealDate();
		var d: DateImpl | undefined;
		if ((d = this._date) && this._offset)
			d = Dates.newInstance(d);
		return d;
	}

	parseDate(
		txt: string,
		fmt: string,
		strict?: boolean,
		refval?: DateImpl,
		localizedSymbols?: zk.LocalizedSymbols,
		tz?: string,
		strictDate?: boolean
	): DateImpl | undefined {
		var d = zk.fmt.Date.parseDate(txt, fmt, strict, refval, localizedSymbols, tz, strictDate);
		if (localizedSymbols) {
			var localeDateTimeFormat = new Intl.DateTimeFormat(localizedSymbols.LAN_TAG, { year: 'numeric' });
			this._offset = localizedSymbols.YDELTA || d ? zk.fmt.Date.getYDelta(d!._moment.toDate(), localeDateTimeFormat) : 0;
		}

		if (this._offset && fmt) {
			if (d instanceof LeapDay) {
				return d.getRealDate();
			}
		}
		return d;
	}

	getYear(): number {
		return this._date!.getFullYear();
	}

	// B70-ZK-2382: in Daylight Saving Time (DST), choose the last time at the end of this mechanism, it will display previous day.
	// e.g. 2014/10/19 at Brasilia (UTC-03:00), it will show 2014/10/18 23:00:00
	// so we need to increase a hour.
	escapeDSTConflict(val?: DateImpl, tz?: string): DateImpl | undefined {
		if (!val) return;
		var newVal = Dates.newInstance(val.getTime() + 3600000, tz); //plus 60*60*1000
		return newVal.getHours() != ((val.getHours() + 1) % 24) ? newVal : val;
	}
}
