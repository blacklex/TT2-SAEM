var placeSearch, autocomplete;
var componentForm = {
    street_number: 'short_name',
    route: 'long_name',
    neighborhood: 'long_name',
    administrative_area_level_3: 'long_name',
    administrative_area_level_1: 'long_name',
    country: 'long_name',
    postal_code: 'short_name',
};

var geocoder;
var map;
var marker;
var infowindow = new google.maps.InfoWindow({size: new google.maps.Size(150, 50)});

function initialize() {
    // Create the autocomplete object, restricting the search
    // to geographical location types.
    autocomplete = new google.maps.places.Autocomplete(
            /** @type {HTMLInputElement} */(document.getElementById('autocomplete')));
    // When the user selects an address from the dropdown,
    // populate the address fields in the form.
    google.maps.event.addListener(autocomplete, 'place_changed', function () {
        codeAddress();
        //fillInAddress();
    });
//----------------------------------------------------------------------

    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(19.455945, -99.212982);
    var mapOptions = {
        zoom: 17,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
    google.maps.event.addListener(map, 'click', function () {
        infowindow.close();

    });

    //----------------------------------------------------------------------

}

function inicializarMapaEditar(latitud, longitud) {
    // Create the autocomplete object, restricting the search
    // to geographical location types.
    autocomplete = new google.maps.places.Autocomplete(
            /** @type {HTMLInputElement} */(document.getElementById('autocomplete')));
    // When the user selects an address from the dropdown,
    // populate the address fields in the form.
    google.maps.event.addListener(autocomplete, 'place_changed', function () {
        codeAddress();
        //fillInAddress();
    });
//----------------------------------------------------------------------
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(latitud, longitud);
    var mapOptions = {
        zoom: 17,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
    google.maps.event.addListener(map, 'click', function (event) {
        infowindow.close();

    });

    //----------------------------------------------------------------------

    if (marker) {
        marker.setMap(null);
        if (infowindow)
            infowindow.close();
    }
    marker = new google.maps.Marker({
        map: map,
        draggable: true,
        position: latlng
    });
    google.maps.event.addListener(marker, 'dragend', function () {
        // updateMarkerStatus('Drag ended');
        geocodePosition(marker.getPosition());
    });

    google.maps.event.trigger(marker, 'click');
}


function inicializarMapaConsultar(latitud, longitud) {
   
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(latitud, longitud);
    var mapOptions = {
        zoom: 17,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
    google.maps.event.addListener(map, 'click', function (event) {
        infowindow.close();

    });

    //----------------------------------------------------------------------

    if (marker) {
        marker.setMap(null);
        if (infowindow)
            infowindow.close();
    }
    marker = new google.maps.Marker({
        map: map,
        draggable: false,
        position: latlng
    });
    google.maps.event.addListener(marker, 'dragend', function () {
        // updateMarkerStatus('Drag ended');
        geocodePosition(marker.getPosition());
    });

    google.maps.event.trigger(marker, 'click');
}


function clone(obj) {
    if (obj == null || typeof (obj) != 'object')
        return obj;
    var temp = new obj.constructor();
    for (var key in obj)
        temp[key] = clone(obj[key]);
    return temp;
}

function geocodePosition(pos) {
    geocoder.geocode({
        latLng: pos
    }, function (responses) {
        llenarDireccion(responses);
        if (responses && responses.length > 0) {
            marker.formatted_address = responses[0].formatted_address;
        } else {
            marker.formatted_address = 'Cannot determine address at this location.';
        }
        infowindow.setContent(marker.formatted_address + "<br>coordinates: " + marker.getPosition().toUrlValue(6));
        infowindow.open(map, marker);

    });
}

function codeAddress() {
    var address = document.getElementById('autocomplete').value;
    geocoder.geocode({'address': address}, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            llenarDireccion(results);

            map.setCenter(results[0].geometry.location);
            if (marker) {
                marker.setMap(null);
                if (infowindow)
                    infowindow.close();
            }
            marker = new google.maps.Marker({
                map: map,
                draggable: true,
                position: results[0].geometry.location
            });
            google.maps.event.addListener(marker, 'dragend', function () {
                // updateMarkerStatus('Drag ended');
                geocodePosition(marker.getPosition());
            });
            google.maps.event.addListener(marker, 'click', function () {
                if (marker.formatted_address) {
                    infowindow.setContent(marker.formatted_address + "<br>coordinates: " + marker.getPosition().toUrlValue(6));
                } else {
                    infowindow.setContent(address + "<br>coordinates: " + marker.getPosition().toUrlValue(6));
                }
                infowindow.open(map, marker);
            });
            google.maps.event.trigger(marker, 'click');

        } else {
            alert('Geocode was not successful for the following reason: ' + status);
        }
        geocodePosition(marker.getPosition());
        
    });
}

function llenarDireccion(results) {
    //Recupera los datos del domicilio como calle, num etc
    var i, j, result, types;

    // Loop through the Geocoder result set. Note that the results
    // array will change as this loop can self iterate.
    for (i = 0; i < results.length; i++) {
        result = results[i];
        types = result.types;
        for (j = 0; j < types.length; j++) {

            var addressType = types[j];
            document.getElementById('latitudY').value = result.geometry.location.lat();
            document.getElementById('longitudX').value = result.geometry.location.lng();


            if (result.long_name === undefined) {
                //Recupera los datos del domicilio como calle, num etc
                for (var i = 0; i < result.address_components.length; i++) {
                    var addressType = result.address_components[i].types[0];


                    if (componentForm[addressType]) {

                        var val = result.address_components[i][componentForm[addressType]];
                        if (addressType === "street_number")
                            document.getElementById('numero').value = val;

                        if (addressType === "route")
                            document.getElementById('calle').value = val;

                        if (addressType === "administrative_area_level_1")
                            document.getElementById('entidadFederativa').value = val;

                        if (addressType === "administrative_area_level_3")
                            document.getElementById('delegacion').value = val;

                        if (addressType === "postal_code")
                            document.getElementById('codigoPostal').value = val;

                        if (addressType === "neighborhood")
                            document.getElementById('colonia').value = val;

                    }
                }

            }

            else {

                if (componentForm[addressType]) {
                    var val = result.long_name;
                    if (addressType === "street_number")
                        document.getElementById('numero').value = val;

                    if (addressType === "route")
                        document.getElementById('calle').value = val;

                    if (addressType === "administrative_area_level_1")
                        document.getElementById('entidadFederativa').value = val;

                    if (addressType === "administrative_area_level_3")
                        document.getElementById('delegacion').value = val;

                    if (addressType === "postal_code")
                        document.getElementById('codigoPostal').value = val;

                    if (addressType === "neighborhood")
                        document.getElementById('colonia').value = val;
                }
            }

        }
    }
    //Fin Recupera los datos del domicilio como calle, num etc

}
