var marker;
var cityCircle;
var markerPaciente = 'images/markers/pacienteMarker.png';
var markerResize = 'images/markers/resizeMarker.png';

function DistaciaWidget(map) {
    //Estblecemos el mapa
    this.set('map', map);
    //Obtenemos el centro de acuerdo a la posicion que tiene el usuario
    this.set('position', map.getCenter());
    //Creamos el marker
    marker = new google.maps.Marker({
                                        draggable:true,
                                        position:latlon,
                                        map:map,
                                        icon:markerPaciente,
                                        animation:google.maps.Animation.DROP,
                                        title:'¡Usted esta aqui!'
                                    });

    // Enlazar la propiedad map del marker con la propiedad map de DistancaWidget
    marker.bindTo('map', this);

    // Enlazar la propiedad position del marker de la propiedad position de DistanciaWidget
    marker.bindTo('position', this);

     // Create a new radius widget
    var radioWidget = new RadioWidget();

    // Bind the radiusWidget map to the DistanceWidget map
    radioWidget.bindTo('map', this);

    // Bind the radiusWidget center to the DistanceWidget position
    radioWidget.bindTo('center', this, 'position');

    // Bind to the radiusWidgets' distance property
    this.bindTo('distance', radioWidget);

    // Bind to the radiusWidgets' bounds property
    this.bindTo('bounds', radioWidget);
}

DistaciaWidget.prototype = new google.maps.MVCObject();

function RadioWidget() {
    //Creamos el circulo
    var circle = new google.maps.Circle({
                                            strokeColor: '#FF0000',
                                            strokeOpacity: 0.8,
                                            strokeWeight: 2
                                        });
    // Establecemos la propiedad distancia, default 5km
    this.set('distance', 5);

    //Enlazar la propiedad bounds de RadioWidget a la propiedad bounds de cirlce
    this.bindTo('bounds', circle);

    //Enlazar el centro del circulo a la propiedad center de RadioWidget
    circle.bindTo('center', this);

    //Enlazar el map cicle a el map de RadioWidget
    circle.bindTo('map', this);

    // Enlazar la propiedad radius de circle a la propiedad radius de RadioWidget
    circle.bindTo('radius', this);
         
    // Agregar el Marker de Tamanio
    this.addTamanio_();
}
RadioWidget.prototype = new google.maps.MVCObject();

RadioWidget.prototype.distance_changed = function() {
    this.set('radius', this.get('distance') * 1000);
};
       
RadioWidget.prototype.addTamanio_ = function() {
    var sizer = new google.maps.Marker({
        icon:markerResize,
        draggable: true,
        title: 'Arrástrame!'
    });
    sizer.bindTo('map', this);
    sizer.bindTo('position', this, 'sizer_position');
    
    var me = this;
    google.maps.event.addListener(sizer, 'drag', function() {
        var min=1;
        var max=10;
        var pos = me.get('sizer_position');
        var center = me.get('center');
        var distance = google.maps.geometry.spherical.computeDistanceBetween(center, pos)/1000;
        if (distance < min) {
            me.set('sizer_position', google.maps.geometry.spherical.computeOffset(center,min*1000,google.maps.geometry.spherical.computeHeading(center,pos))); 
        } 
        else if (distance > max){
            me.set('sizer_position', google.maps.geometry.spherical.computeOffset(center,max*1000,google.maps.geometry.spherical.computeHeading(center,pos)));
        }
        // Set the circle distance (radius)
        me.setDistance();
    });
};

RadioWidget.prototype.center_changed = function() {
    var bounds = this.get('bounds');
    // Bounds might not always be set so check that it exists first.
    if (bounds) {
        var lng = bounds.getNorthEast().lng();
        // Put the sizer at center, right on the circle.
        var position = new google.maps.LatLng(this.get('center').lat(), lng);
        this.set('sizer_position', position);
    }
};
      
RadioWidget.prototype.distanceBetweenPoints_ = function(p1, p2) {
    if (!p1 || !p2) {
        return 0;
    }
    
    var R = 6371; // Radius of the Earth in km
    var dLat = (p2.lat() - p1.lat()) * Math.PI / 180;
    var dLon = (p2.lng() - p1.lng()) * Math.PI / 180;
    var a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(p1.lat() * Math.PI / 180) * Math.cos(p2.lat() * Math.PI / 180) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    var d = R * c;
    return d;
  };
      
 RadioWidget.prototype.setDistance = function() {
     // As the sizer is being dragged, its position changes.  Because the
     // RadiusWidget's sizer_position is bound to the sizer's position, it will
     // change as well.
     var pos = this.get('sizer_position');
     var center = this.get('center');
     var distance = this.distanceBetweenPoints_(center, pos);

     // Set the distance property for any objects that are bound to it
     this.set('distance', distance);
 };

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
        var distaciaWidget = new DistaciaWidget(map);
        google.maps.event.addListener(distaciaWidget, 'distance_changed', function() {
            displayInfo(distaciaWidget);
        });
        
//        var populationOptions = {
//                                    strokeColor: '#FF0000',
//                                    strokeOpacity: 0.8,
//                                    strokeWeight: 2,
//                                    fillColor: '#FF0000',
//                                    fillOpacity: 0.35,
//                                    map: map,
//                                    center: latlon,
//                                    radius: 5000
//                                  };
//
//        cityCircle = new google.maps.Circle(populationOptions);

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

function displayInfo(widget) {
    var info = document.getElementById('info');
    info.innerHTML = '<span class="info-box-text">Informacion de ubicacion</span>' + 
                     '<span class="info-box-number"> ' + Math.round(widget.get('distance')) + 'km</span>' +
                     '<div class="progress">' +
                     '  <div class="progress-bar" style="width: 100%">' +
                     '  </div>' +
                     '</div>' +
                     '<span class="progress-description">Distancia---> Min: 1km - - - Max: 10km</span>';
}
      
google.maps.event.addDomListener(window, 'load', initialize);
