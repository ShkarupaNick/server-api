function onLoadMostPopular() {
    $.get("http://couchtalks.com:8080/item/list?date=2017-03-28", function (data) {
        for (var item of data) {
            var div = document.createElement("div");
            div.setAttribute("class", "col-md-3 col-sm-3");
            var a = document.createElement("a");
            a.setAttribute("href", "content_page.html");
            var img = document.createElement("img");
            img.setAttribute("class", "img-responsive img-galery img-hover");
            img.setAttribute("src", item.show.image.medium);
            a.append(img);
            div.append(a);
            var pFooter = document.createElement("div");
            pFooter.setAttribute("class", "panel-footer");
            var h4 = document.createElement("h4");
            h4.append(document.createTextNode("".concat(item.show.network.name)));
            var h4_1 = document.createElement("h4");
            h4_1.append(document.createTextNode(item.airtime));
            var h5 = document.createElement("h5");
            h5.append(document.createTextNode(item.show.name));
            pFooter.append(h4);
            pFooter.append(h4_1);
            pFooter.append(h5);
            div.append(pFooter);
            $("[name='mpop2']").append(div);
        }
    }, "json");
}

function onLoadLive() {
    $.get("http://couchtalks.com:8080/item/list?date=2017-03-28", function (data) {
        for (var item of data) {
            var div = document.createElement("div");
            div.setAttribute("class", "row");
            var col = document.createElement("div");
            col.setAttribute("class", "col-sm-3");
            var a = document.createElement("a");
            a.setAttribute("href", "#");
            var img = document.createElement("img");
            img.setAttribute("class", "img-responsive img-portfolio img-hover");
            img.setAttribute("src", item.show.image.medium);
            a.append(img);
            var div2 = document.createElement("div");
            div2.setAttribute("class", "col-rg-3");
            var rtext = document.createElement("div");
            rtext.setAttribute("class", "record-text");
            var h5_1 = document.createElement("h5");
            h5_1.append(document.createTextNode("".concat(item.show.network.name, " ", item.airtime)));
            var h5_2 = document.createElement("h5");
            h5_2.append(document.createTextNode(item.show.name));
            rtext.append(h5_1)
            rtext.append(h5_2);
            col.append(a);
            div2.append(rtext);
            div.append(col);
            div.append(div2);
            $("[name='live']").append(div);
        }
    }, "json");
}


$(function() {

    // We can attach the `fileselect` event to all file inputs on the page
    $(document).on('change', ':file', function() {
        var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        input.trigger('fileselect', [numFiles, label]);
    });

    // We can watch for our custom `fileselect` event like this
    $(document).ready( function() {
        $(':file').on('fileselect', function(event, numFiles, label) {

            var input = $(this).parents('.input-group').find(':text'),
                log = numFiles > 1 ? numFiles + ' files selected' : label;

            if( input.length ) {
                input.val(log);
            } else {
                if( log ) alert(log);
            }

        });
    });

});

