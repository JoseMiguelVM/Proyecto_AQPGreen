package com.example.aqpgreen.ui.Estadisticas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class VistaGraficoCircular extends View {
    float start=0;
    int[] data;
    String[] lugares;
    int numberOfparts;
    private int[] color;

    public VistaGraficoCircular(Context context, int numOfItems, int[] data, int[] color, String[] lugares) {//partimos
        super(context);
        setFocusable(true);
        this.numberOfparts=numOfItems;
        this.data=data;
        this.color=color;
        this.lugares=lugares;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (data.length==0)
            return;
        super.onDraw(canvas);
        //canvas.drawColor(Color.WHITE);
        Paint p = new Paint();
        float[] scaledValues = scale();
        
        RectF rectF = new RectF(100,100,getWidth()-100,getWidth()-100);
        int centerX = (int)(rectF.left + rectF.right) / 2;
        int centerY = (int)(rectF.top + rectF.bottom) / 2;
        int radius = (int)(rectF.right - rectF.left) / 2;
        for(int i=0;i<numberOfparts;i++){
            p.setColor(color[i]);
            p.setStyle(Paint.Style.FILL);
            canvas.drawArc(rectF,start,scaledValues[i],true,p);
            p.setColor(Color.BLACK);
            float medianAngle = (start + (scaledValues[i] / 2f)) * (float)Math.PI / 180f;
            if(data[i]!=0) {
                p.setTextSize(40);
                if  ((medianAngle>=0 && medianAngle<=Math.PI/2) || (medianAngle>=0 && medianAngle<=Math.PI/2))
                    p.setTextAlign(Paint.Align.RIGHT);
                else
                    p.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(lugares[i]+":"+data[i], (float) (centerX + (radius * Math.cos(medianAngle))), (float) (centerY + (radius * Math.sin(medianAngle))), p);

            }
            start=start+scaledValues[i];
        }

    }

    private float[] scale() {
        float[] scaledValues = new float[this.data.length];
        float total = getTotal();
        for (int i = 0; i < this.data.length; i++) {
            scaledValues[i] = (this.data[i] / total) * 360; //Scale each value
        }
        return scaledValues;
    }

    private float getTotal() {
        float total = 0;
        for (float val : this.data)
            total += val;
        return total;
    }




}
