$(document).ready(function () {
    $('div.tabs').each(function () {
        var $active, $content, $links = $(this).find('a');
        console.log(localStorage.getItem('activeTab'));
        $active = $($links.filter('[href="' + localStorage.getItem('activeTab') + '"]')[0] || $links[0]);
        $active.parent().addClass('is-active');
        $content = $($active[0].hash);

        $links.not($active).each(function () {
            $(this.hash).hide();
        });

        $(this).on('click', 'a', function (e) {
            $active.parent().removeClass('is-active');
            $content.hide();
            $active = $(this);
            localStorage.setItem('activeTab', this.hash);
            $content = $(this.hash);
            $active.parent().addClass('is-active');
            $content.show();
            e.preventDefault();
        });
    });

    $('#warn_gl_cap_btn').on('click', function () {
        $('#warn_gl_cap').hide()
    })
    $('#warn_cache_btn').on('click', function () {
        $('#warn_cache').hide()
    })
});


let new_table_row = 0;
let new_dynamic_row = 0;


$('#add_button').on('click', function () {

    $('#dataset_body').prepend($('#cenas').text().replace(/#NUMBER#/g, new_table_row++));
    $('.delete_new_row').on('click', function () {
        $("#new_row" + this.id).remove()
    })

});

$('#add_dynamic_button').on('click', function () {

    $('#dynamic_body').prepend($('#dynamicTemplate').text().replace(/#NUMBER#/g, new_dynamic_row++));
    $('.delete_new_row').on('click', function () {
        $("#new_dynamic_row" + this.id).remove()
    })

});


$('.delete_row').on('click', function () {
    console.log("DELETE:" + this.id);
    let id = this.id;
    $.post('deleteDataset', {'id': this.id}, function () {
        console.log(id);
        $("#row" + id).remove();
    });
});

$('.delete_dynamic_row').on('click', function () {
    console.log("DELETE Dynamic:" + this.id);
    let id = this.id;
    $.post('deleteDynamicService', {'alias': this.id}, function () {
        console.log(id);
        $("#row_dynamic" + id).remove();
    });
});

