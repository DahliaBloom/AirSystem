# Air System
*Ein Flug-Assistent, der dir vom Buchen deines Fluges, bestellen von Essen während des Fluges bis hin zum Besuchen von Sehenswürdigkeiten unter die Arme greift.*

**Funktionen**
- Buchen von Flügen
- Echtzeitverfolgung von aktuellem Flug mit Karte
- Wetter des Ankunftsortes
- Verspätung des Fluges anzeigen
- Im Restaurant Essen bestellen
- Feedback über Flug geben
- Sicherheitsinstruktionen abrufen
- Sehenswürdigkeiten des Ankunftsortes auf einer Karte anzeigen lassen und speichern

Die App verwendet JavaFX für die GUI und Springboot für den Server.
Zudem werden APIs von Google und ArcGIS einbezogen.

**System requirements:
Javafx SDK (may have to be added to VM options manually: Run->edit configurations-> modify options->add VM options;
                paste '--module-path "C:\your\file\path\javafx-sdk-18.0.1\lib" --add-modules javafx.controls,javafx.fxml')
up to-date Microsoft Visual C++ Redistributable, if not installed look up on the [link](https://docs.microsoft.com/en-us/cpp/windows/latest-supported-vc-redist?view=msvc-170)
How to Execute:
Run server with MainResource.java
Enter build.gradle file
Execute "run" (line 115)
login with
Username: *tom*
Password: *password***
