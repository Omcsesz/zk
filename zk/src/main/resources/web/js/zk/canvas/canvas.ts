/* canvas.ts

	Purpose:
		
	Description:
		
	History:
		Mon Dec 28 12:26:39 TST 2009, Created by tomyeh

Copyright (C) 2009 Potix Corporation. All Rights Reserved.

*/
/** Canvas - 2D command-based drawing.
 * Specifying this package in the depends attribute if one of your packages
 * would like to use the canvas function. For example,
 * ```xml
 * //zk.wpd
 * <package name="foo.whatever" language="xul/html" depends="zk.canvas">
 * ```
 */
//zk.$package('zk.canvas');
/** @class zk.canvas.Canvas
 * Utilities to create and manipulate the canvas element.
 * @since 5.0.2
 */
export var Canvas = {
	/**
	 * Creates a canvas element.
	 * <p>For example,
	 *```ts
	 * var main = jq("#main");
	 * main.append(canvas);
	 * var canvas = zk.canvas.Canvas.create(main.clientWidth, main.clientHeight);
	 * var ctx = canvas.getContext("2d");
	 * ctx.fillStyle = "rgb(200,0,0)";
	 * ctx.fillRect (10, 10, 55, 50);
	 *
	 * ctx.fillStyle = "rgba(0, 0, 200, 0.5)";
	 * ctx.fillRect (30, 30, 255, 50);
	 * ```
	 *
	 * @param width - the width. Ignored if not specified.
	 * Notice that it accepts only integer (unlike other DOM element)
	 * @param height - the height. Ignored if not specified.
	 * Notice that it accepts only integer (unlike other DOM element)
	 */
	create(width?: number, height?: number): HTMLCanvasElement {
		var el = document.createElement('canvas');
		if (width) el.width = zk.parseInt(width);
		if (height) el.height = zk.parseInt(height);
		return el;
	}
};
zk.canvas.Canvas = Canvas;