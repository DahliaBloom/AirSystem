package common.util;

/*
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.MapView;

import common.model.PlaceOfInterest;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
*/

import client.Location;
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import common.model.PlaceOfInterest;


public class WorldMap {


    // if "JavaFX runtime components are missing, and are required to run this application"
    // RUN->edit configurations->Modify options->add VM options
    //paste following into VM options field:
    //--module-path "C:\your\file\path\javafx-sdk-18.0.1\lib" --add-modules javafx.controls,javafx.fxml
/***********************************************************************************
 * How to use this class:
* A: Call in order to display Travelroute
* 1. Call method WorldMap.setTravelroute(double a,double b,double c,double d)
* a: Longitude start, b: Latitude Start , c Longitude end , Latitude end
* 2. call Worldmap.main (/)
* B: Call in order to display POIs in end location
* 1. call getPOIs/getPOIsCultural/getPOIsNature/getPOIsArchitecture(String q)
* q: your query in lowercase letters (eg. "munich","tokyo",etc.)
* 2. call Worldmap.main() (/)
***********************************************************************************/

    static double x0;
    static double y0=90;

    static double x1;
    static double y1=90;

    static double x2;
    static double y2=90;

    static  double x3;
    static double y3=90;

    static double x4;
    static double y4=90;

    static double x5;
    static double y5=90;

    static double x6;
    static  double y6=90;

    static  double x7;
    static double y7=90;

    static double x8;
    static double y8=90;

    static double x9;
    static double y9=90;

    static  double startx = 90.0;
    static  double endx = 90.0;

    static double starty = 90.0;
    static double endy = 90.0;

    static double zoomlevel=144448.0;

    static double xview = (x0+x1+x2+x3+x4+x5+x6+x7+x8+x9)/10.0;
    static double yview = (y0+y1+y2+y3+y4+y5+y6+y7+y8+y9)/10.0;

    Point point0 = new Point(x0, y0, SpatialReferences.getWgs84());
    Point point1 = new Point(x1, y1, SpatialReferences.getWgs84());
    Point point2 = new Point(x2, y2, SpatialReferences.getWgs84());
    Point point3 = new Point(x3, y3, SpatialReferences.getWgs84());
    Point point4 = new Point(x4, y4, SpatialReferences.getWgs84());
    Point point5 = new Point(x5, y5, SpatialReferences.getWgs84());
    Point point6 = new Point(x6, y6, SpatialReferences.getWgs84());
    Point point7 = new Point(x7, y7, SpatialReferences.getWgs84());
    Point point8 = new Point(x8, y8, SpatialReferences.getWgs84());
    Point point9 = new Point(x9, y9, SpatialReferences.getWgs84());

    private MapView mapView;

        private final String apiKey = "AAPKc3eaa78cb0c74b808f46efe58316106elzxLoj52LRXUL2w2So1q5phH5YoKgvwD55lWLXtWMe5Fv-XIVWfmxyrAXwzfTBJw";

    /*******************************************************************
     * used for displaying the route of any flight, automatically adjusts zoom
     * level and "Camera position"
     *
     * @param start :   departure location
     * @param end   :   flight destination
     ******************************************************************/

    public static void setTravelroute(client.Location start, client.Location end){
        //double sx,double sy, double ex, double ey

      /*  client.Location start= Location.Munich;
        client.Location end= Location.Rome;*/

        if(start== Location.Munich){
            startx = 11.775028;
            starty =48.353662 ;
        }
        if(start == Location.Rome){
            startx = 12.590686;
            starty =41.799361 ;
        }
        if(start== Location.Berlin){
            startx = 13.508999;
            starty =52.364044 ;
        }
        if(start== Location.Paris){
            startx = 2.3522219;
            starty =48.856614 ;
        }
        if(start== Location.New_York_City){
            startx = -73.7781;
            starty =40.6413 ;
        }

        if(end== Location.Munich){
            endx = 11.775028;
            endy =48.353662 ;
        }
        if(end == Location.Rome){
            endx = 12.590686;
            endy =41.799361 ;
        }
        if(end== Location.Berlin){
            endx = 13.508999;
            endy =52.364044 ;
        }
        if(end== Location.Paris){
            endx = 2.3522219;
            endy =48.856614 ;
        }
        if(end== Location.New_York_City){
            endx = -73.7781;
            endy =40.6413 ;
        }


        double n = Math.sqrt(Math.pow((startx-endx),2)+Math.pow((starty-endy),2));
        setViewpoint((startx+endx)/2,(starty+endy)/2);

        int test = (int) (n/5);

        modifyZoomLevel(30+test*60);


    }

    /***************************
     *not to be called manually
     ***************************/
    public static void modifyZoomLevel(double n){

    zoomlevel= zoomlevel*n;
    }


    /***********************************************************
     * call this to wipe route/POIs from map
     **********************************************************/
    public static void resetMap(){
          y0=90;
          y1=90;
          y2=90;
          y3=90;
          y4=90;
          y5=90;
          y6=90;
          y7=90;
          y8=90;
          y9=90;

          starty= 90;
          endy = 90;
    }

    /***************************
     *not to be called manually
     ***************************/
    public static void setViewpoint(double x, double y){
        xview = x;
        yview = y;
    }

    /***************************
     *not to be called manually
     ***************************/
    public static void adjustpoints(PlaceOfInterest[] pois){
      // System.out.println(pois[0].getCoordinates()[0]);

        if(pois.length > 0) {

            x0 = pois[0].getCoordinates()[0];
            y0 = pois[0].getCoordinates()[1];


            x1 = pois[1].getCoordinates()[0];
            y1 = pois[1].getCoordinates()[1];

            x2 = pois[2].getCoordinates()[0];
            y2 = pois[2].getCoordinates()[1];

            x3 = pois[3].getCoordinates()[0];
            y3 = pois[3].getCoordinates()[1];

            x4 = pois[4].getCoordinates()[0];
            y4 = pois[4].getCoordinates()[1];

            x5 = pois[5].getCoordinates()[0];
            y5 = pois[5].getCoordinates()[1];

            x6 = pois[6].getCoordinates()[0];
            y6 = pois[6].getCoordinates()[1];

            x7 = pois[7].getCoordinates()[0];
            y7 = pois[7].getCoordinates()[1];

            x8 = pois[8].getCoordinates()[0];
            y8 = pois[8].getCoordinates()[1];

            x9 = pois[9].getCoordinates()[0];
            y9 = pois[9].getCoordinates()[1];

            xview = (x0 + x1 + x2 + x3 + x4 + x5 + x6 + x7 + x8 + x9) / 10.0;
            yview = (y0 + y1 + y2 + y3 + y4 + y5 + y6 + y7 + y8 + y9) / 10.0;
        }
    }


    //standard start methode, setzt stage auf, initialisiert die Map, gibt Map weiter
    public MapView getMap()  {
        ArcGISRuntimeEnvironment.setApiKey(apiKey);


        mapView = new MapView();

        ArcGISMap map = new ArcGISMap(BasemapStyle.ARCGIS_TOPOGRAPHIC);


        mapView.setMap(map);

        mapView.setViewpoint(new Viewpoint(yview, xview,zoomlevel));
    //48.13743, 11.57549


        GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(graphicsOverlay);




        SimpleMarkerSymbol simpleMarkerSymbol =
                new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF5733, 10);
        SimpleLineSymbol blueOutlineSymbol =
                new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFF0063FF, 2);

        simpleMarkerSymbol.setOutline(blueOutlineSymbol);


        Graphic pointGraphic0 = new Graphic(point0, simpleMarkerSymbol);
        Graphic pointGraphic1 = new Graphic(point1, simpleMarkerSymbol);
        Graphic pointGraphic2 = new Graphic(point2, simpleMarkerSymbol);
        Graphic pointGraphic3 = new Graphic(point3, simpleMarkerSymbol);
        Graphic pointGraphic4 = new Graphic(point4, simpleMarkerSymbol);
        Graphic pointGraphic5 = new Graphic(point5, simpleMarkerSymbol);
        Graphic pointGraphic6 = new Graphic(point6, simpleMarkerSymbol);
        Graphic pointGraphic7 = new Graphic(point7, simpleMarkerSymbol);
        Graphic pointGraphic8 = new Graphic(point8, simpleMarkerSymbol);
        Graphic pointGraphic9 = new Graphic(point9, simpleMarkerSymbol);


        // add the point graphic to the graphics overlay
        graphicsOverlay.getGraphics().add(pointGraphic0);
        graphicsOverlay.getGraphics().add(pointGraphic1);
        graphicsOverlay.getGraphics().add(pointGraphic2);
        graphicsOverlay.getGraphics().add(pointGraphic3);
        graphicsOverlay.getGraphics().add(pointGraphic4);
        graphicsOverlay.getGraphics().add(pointGraphic5);
        graphicsOverlay.getGraphics().add(pointGraphic6);
        graphicsOverlay.getGraphics().add(pointGraphic7);
        graphicsOverlay.getGraphics().add(pointGraphic8);
        graphicsOverlay.getGraphics().add(pointGraphic9);

        PointCollection polylinePoints = new PointCollection(SpatialReferences.getWgs84());
        polylinePoints.add(new Point(startx,starty));
        polylinePoints.add(new Point(endx, endy));

        Polyline polyline = new Polyline(polylinePoints);

        // create a blue line symbol for the polyline
        SimpleLineSymbol polylineSymbol =
                new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFF0063FF, 3);

        Graphic polylineGraphic = new Graphic(polyline, polylineSymbol);

        graphicsOverlay.getGraphics().add(polylineGraphic);

        return mapView;
    }


}
