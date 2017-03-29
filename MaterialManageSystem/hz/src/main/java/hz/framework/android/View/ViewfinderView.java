/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hz.framework.android.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import hz.framework.android.R;

/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder
 * rectangle and partial transparency outside it, as well as the laser scanner
 * animation and result points.
 * 
 */
public final class ViewfinderView extends View implements Runnable {

	private Canvas bufferCanvas;
	private Bitmap bufferBitmap;
	private Bitmap bitmap;

	public ViewfinderView(Context context) {
		super(context);
		init();
	}

	public ViewfinderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ViewfinderView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init(){
		bitmap = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.barcode_laser_line);
		new Thread(this).start();
	}

	Rect srcRect;
	RectF destRect;
	private float curY,maxY;
	Paint p = new Paint();
	@Override
	protected void onDraw(Canvas canvas) {

		if (bufferBitmap == null){
			bufferBitmap = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_4444);
			bufferCanvas = new Canvas(bufferBitmap);
		}else{
			bufferBitmap.recycle();
			bufferBitmap = null;
			bufferBitmap = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_4444);
			bufferCanvas = new Canvas(bufferBitmap);
		}
		if(maxY == 0){
			maxY = getHeight();
		}

		if(srcRect == null){
			srcRect = new Rect();
			srcRect.set(0,0,bitmap.getWidth(),bitmap.getHeight());
		}
		if(destRect == null){
			destRect = new RectF();
		}
		if(curY>=maxY){
			curY = 0;
		}
		destRect.set(0,curY-bitmap.getHeight(),getWidth(),curY);
		curY+=10;

		bufferCanvas.drawBitmap(bitmap,srcRect,destRect,null);

		canvas.drawBitmap(bufferBitmap,0,0,null);

//		postInvalidate();
	}


	@Override
	public void run() {
		while(true){
			postInvalidate();

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
