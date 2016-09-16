var $document, $window, $body;

(function($){
    $document = $(document);
    $window = $(window);
    $body = $('body');

    $(function(){
        resetContentVisual();
    });

    // Dimensionnement des images du bloc section du milieu
    function resetContentVisual(){
        $('.content-visual').each(function(){
            var $this = $(this);
            $this.css({'height' : $this.parents('.content-item').height() + "px"});
        });
    }
})(jQuery);