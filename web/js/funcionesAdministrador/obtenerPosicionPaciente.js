/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
        var marker;
        var cityCircle;
        var image = 'images/markers/pacienteMarker.png';

        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(mostrarUbicacion);
        }
        else {
            alert("¡Error! Este navegador no soporta la Geolocalización.");
        }
        
        function mostrarUbicacion(position) {
            var times = position.timestamp;
            var latitud = position.coords.latitude;
            var longitud = position.coords.longitude;
            var altitud = position.coords.altitude;	
            var exactitud = position.coords.accuracy;	
//            var div = document.getElementById("ubicacion");
//            div.innerHTML = "Timestamp: " + times + "<br>Latitud: " + latitud + "<br>Longitud: " + longitud + "<br>Altura en metros: " + altitud + "<br>Exactitud: " + exactitud;
        }

        function refrescarUbicacion() {
            navigator.geolocation.watchPosition(mostrarUbicacion);
        }
        
        var x=document.getElementById("demo");
        navigator.geolocation.getCurrentPosition(showPosition,showError);
        function showPosition(position) {
            lat = position.coords.latitude;
            lon = position.coords.longitude;
            latlon = new google.maps.LatLng(lat, lon)
            mapholder = document.getElementById('mapholder')
            mapholder.style.height='100%';
            mapholder.style.width='100%';
            var myOptions = {
                center:latlon,zoom:10,
                mapTypeId:google.maps.MapTypeId.ROADMAP,
                mapTypeControl:false,
                navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}
            };
            
            var map = new google.maps.Map(document.getElementById("mapholder"),myOptions);
            marker = new google.maps.Marker({
                                            position:latlon,
                                            map:map,
                                            icon:image,
                                            animation:google.maps.Animation.DROP,
                                            title:"¡Usted esta aqui!"});
            var populationOptions = {
                                    strokeColor: '#FF0000',
                                    strokeOpacity: 0.8,
                                    strokeWeight: 2,
                                    fillColor: '#FF0000',
                                    fillOpacity: 0.35,
                                    map: map,
                                    center: latlon,
                                    radius: Math.sqrt(81) * 100
                                  };

            cityCircle = new google.maps.Circle(populationOptions);

            google.maps.event.addListener(marker, 'click', toggleBounce);
        }
        
        function toggleBounce() {
            if (marker.getAnimation() != null) {
                marker.setAnimation(null);
            }
            else {
                marker.setAnimation(google.maps.Animation.BOUNCE);
            }
        }

    google.maps.event.addDomListener(window, 'load', initialize);
        
        function showError(error) {
            switch(error.code) {
                case error.PERMISSION_DENIED:
                    x.innerHTML="Denegada la peticion de Geolocalización en el navegador."
                break;
                case error.POSITION_UNAVAILABLE:
                    x.innerHTML="La información de la localización no esta disponible."
                break;
                case error.TIMEOUT:
                    x.innerHTML="El tiempo de petición ha expirado."
                break;
                case error.UNKNOWN_ERROR:
                    x.innerHTML="Ha ocurrido un error desconocido."
                break;
            }
        }    
    });