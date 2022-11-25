package com.example.aqpgreen.ui.Estadisticas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class VistaGraficoCircular extends View {
    float start=0; // donde comienza el dibujo
    int width; //Ancho
    int[] data; //Arreglo de datos
    int cx,cy; // Coordenadas
    int numberOfparts;// dice que muchos datos o elementos se colocarán en el gráfico
    private int[] color; //Colores de los datos

    //Constructor
    public VistaGraficoCircular(Context context, int numOfItems, int[] data, int[] color) {
        super(context);
        setFocusable(true);
        this.numberOfparts=numOfItems;
        this.data=data;
        this.color=color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        Paint p = new Paint();
        float[] scaledValues = scale();
        
        RectF rectF = new RectF(100,100,getWidth()-100,getWidth()-100);

        for(int i=0;i<numberOfparts;i++){
            p.setColor(color[i]);
            p.setStyle(Paint.Style.FILL);
            canvas.drawArc(rectF,start,scaledValues[i],true,p);
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
