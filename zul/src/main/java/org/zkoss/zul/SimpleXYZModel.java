/* SimpleXYZModel.java

	Purpose:
		
	Description:
		
	History:
		Sun Feb 10 13:52:25     2008, Created by henrichen

Copyright (C) 2008 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under LGPL Version 2.1 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.zul;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.event.ChartDataEvent;

/**
 * A XYZ data model implementation of {@link XYZModel}.
 * A XYZ model is an N series of (X, Y, Z) data objects .
 *
 * @author henrichen
 * @see XYModel
 * @see Chart
 * @since 3.5.0
 */
public class SimpleXYZModel extends SimpleXYModel implements XYZModel {
	private static final long serialVersionUID = 20091008183739L;

	//-- XYModel --//
	/** Not supported since we need not only x, y, but also z information.
	 */
	public void addValue(Comparable<?> series, Number x, Number y) {
		throw new UnsupportedOperationException("Use addValue(series, x, y, z) instead!");
	}

	/** Not supported since we need not only x, y, but also z information.
	 */
	public void addValue(Comparable<?> series, Number x, Number y, int index) {
		throw new UnsupportedOperationException("Use addValue(series, x, y, z, index) instead!");
	}
	
	public void addValue(Comparable<?> series, Number x, Number y, Number z) {
		addValue(series, x, y, z, -1);
	}
	
	public void addValue(Comparable<?> series, Number x, Number y, Number z, int index) {
		int cIndex = addValue0(series, x, y, z, index);
		fireEvent(ChartDataEvent.ADDED, series, (Comparable<?>) x, _seriesList.indexOf(series), cIndex,
				_seriesMap.get(series).get(cIndex).toNumbers());
	}

	/** Not supported since we need not only x, y, but also z information.
	 */
	public void setValue(Comparable<?> series, Number x, Number y, int index) {
		throw new UnsupportedOperationException("Use setValue(series, x, y, z, index) instead!");
	}
	
	public void setValue(Comparable<?> series, Number x, Number y, Number z, int index) {
		removeValueForXYZ(series, index);
		int cIndex = addValue0(series, x, y, z, index);
		fireEvent(ChartDataEvent.CHANGED, series, (Comparable<?>) x, _seriesList.indexOf(series), cIndex,
				_seriesMap.get(series).get(cIndex).toNumbers());
	}

	//-- XYZModel --//
	public Number getZ(Comparable<?> series, int index) {
		final List<XYPair> xyzTuples = _seriesMap.get(series);

		if (xyzTuples != null) {
			return ((XYZTuple) xyzTuples.get(index)).getZ();
		}
		return null;
	}

	private int addValue0(Comparable<?> series, Number x, Number y, Number z, int index) {
		List<XYPair> xyzTuples = _seriesMap.get(series);
		if (xyzTuples == null) {
			xyzTuples = new ArrayList<XYPair>();
			_seriesMap.put(series, xyzTuples);
			_seriesList.add(series);
		}
		int cIndex = index;
		if (index >= 0)
			xyzTuples.add(index, new XYZTuple(x, y, z));
		else {
			cIndex = xyzTuples.size();
			xyzTuples.add(new XYZTuple(x, y, z));
		}
		return cIndex;
	}

	public void removeValue(Comparable<?> series, int index) {
		XYZTuple xyz = removeValueForXYZ(series, index);
		if (xyz != null)
			fireEvent(ChartDataEvent.REMOVED, series, (Comparable<?>) xyz.getX(), _seriesList.indexOf(series), index,
					xyz.toNumbers());
		else
			fireEvent(ChartDataEvent.REMOVED, series, null, _seriesList.indexOf(series), -1, null);
	}

	private XYZTuple removeValueForXYZ(Comparable<?> series, int index) {
		List<XYPair> xyzTuples = _seriesMap.get(series);
		if (xyzTuples == null) {
			return null;
		}
		return (XYZTuple) xyzTuples.remove(index);
	}

	//-- internal class --//
	private static class XYZTuple extends XYPair {
		private static final long serialVersionUID = 20091008183759L;
		private Number _z;

		private XYZTuple(Number x, Number y, Number z) {
			super(x, y);
			_z = z;
		}

		public Number getZ() {
			return _z;
		}

		public Number[] toNumbers() {
			return new Number[] { getX(), getY(), _z };
		}
	}
}
