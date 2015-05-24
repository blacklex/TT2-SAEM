var marker;
var cityCircle;
var image = 'images/markers/pacienteMarker.png';

function initialize() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition,showError);
    }
    else {
        alert("¡Error! Este navegador no soporta la Geolocalización.");
    }
    
    function showPosition(position) {
        lat = position.coords.latitude;
        lon = position.coords.longitude;
        latlon = new google.maps.LatLng(lat, lon);
        mapcanvas = document.getElementById('map_canvas')
        mapcanvas.style.height='700px';
     
        var mapOptions = {
            center:latlon,
            zoom:13,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        }
        map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
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
                                    radius: 5000
                                  };

        cityCircle = new google.maps.Circle(populationOptions);

        google.maps.event.addListener(marker, 'mouseout', toggleBounce);
    }
    
    function toggleBounce() {
        if (marker.getAnimation() !== null) {
            marker.setAnimation(null);
        }
        else {
            marker.setAnimation(google.maps.Animation.BOUNCE);
        }
    }
    function showError(error) {
        switch(error.code) {
            case error.PERMISSION_DENIED:
                alert("Denegada la peticion de Geolocalización en el navegador.");
            break;
            case error.POSITION_UNAVAILABLE:
                alert("La información de la localización no esta disponible.");
            break;
            case error.TIMEOUT:
                alert("El tiempo de petición ha expirado.");
            break;
            case error.UNKNOWN_ERROR:
                alert("Ha ocurrido un error desconocido.");
            break;
        }
    }
}
google.maps.event.addDomListener(window, 'load', initialize);
