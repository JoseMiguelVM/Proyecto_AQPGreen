package com.example.aqpgreen.ui.Estadisticas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class miPropiaVista extends View {
    float start=0; // donde comienza el dibujo
    int width; //Ancho
    int[] data; //Arreglo de datos
    int cx,cy; // Coordenadas
    int numberOfparts;// dice que muchos datos o elementos se colocarán en el gráfico
    private int[] color; //Colores de los datos

    //Constructor
    public miPropiaVista(Context context, int numOfItems, int[] data, int[] color) {
        super(context);
        setFocusable(true);
        this.numberOfparts=numOfItems;
        this.data=data;
        this.color=color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Creamos un canvas donde le asignamos el color blanco
        canvas.drawColor(Color.WHITE);
        //Creamos un objeto de la clase paint
        Paint p = new Paint();
        //creamos un arreglo donde tendremos los valores que tendra cada sección
        float[] scaledValues = scale();
        
        //Esta clase nos ayuda a crear un rectangulo
        RectF rectF = new RectF(100,100,getWidth()-100,getWidth()-100);

        //Este arreglo nos sirve para asignar un color a cada dato
        for(int i=0;i<numberOfparts;i++){
            p.setColor(color[i]);
            p.setStyle(Paint.Style.FILL);
            //Una guía visual
            canvas.drawArc(rectF,start,scaledValues[i],true,p);
            start=start+scaledValues[i];
        }

        //Este es para crear el circulo de adentro
        Paint cenPaint=new Paint();
        //partiremos de los datos del circulo mas grande
        int radius=getWidth()/2-280;
        cenPaint.setStyle(Paint.Style.FILL);
        cenPaint.setColor(Color.WHITE);
        cx=cy=getWidth()/2;
        canvas.drawCircle(cx,cy,radius,cenPaint);
    }

    //Este metodo nos sirve para calcular el angulo que tendra cada dato
    private float[] scale() {
        //creamos un arreglo para los datos
        float[] scaledValues = new float[this.data.length];
        //obtenemos el total de elementos del arreglo
        float total = getTotal();
        //Bucle para sacar el angulo correspondiente a cada dato
        for (int i = 0; i < this.data.length; i++) {
            scaledValues[i] = (this.data[i] / total) * 360; //Scale each value
        }
        //retorna el arreglo con los angulos
        return scaledValues;
    }

    //Este es el metodo que nos devuelve el total de la suma de los datos
    private float getTotal() {
        float total = 0;
        for (float val : this.data)
            total += val;
        return total;
    }




}
