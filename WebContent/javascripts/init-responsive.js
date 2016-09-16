var $document, $window, $body, $mainNav;
var RESOLUTION_DESKTOP = 992;
var RESIZE_CALLBACK_DELAY = 10;

// Permet de limiter le nombre de callbacks sur la méthode resize
var debounce = function (func, threshold, execAsap) {
    var timeout;
    return function debounced () {
        var obj = this, args = arguments;
        function delayed () {
            if (!execAsap){
                func.apply(obj, args);
            }
            timeout = null;
        }

        if (timeout)
            clearTimeout(timeout);
        else if (execAsap){
            func.apply(obj, args);
        }
        timeout = setTimeout(delayed, threshold || RESIZE_CALLBACK_DELAY);
    };
};

(function($, sr){
    $document = $(document);
    $window = $(window);
    $body = $('body');

    var $focusContentTitle, $focusArticle;
    $(function(){
        $mainNav = $('#main-nav');
        $focusContentTitle = $('.focus-title');
        $focusArticle = $('.focus-article');

        /* ==========================================================================
         SMART RESIZER
         ========================================================================== */

        // Gestion du changement de résolution
        $window.smartresize(function(){
            resetFocusArticle();
            if($window.width() > RESOLUTION_DESKTOP){
                resetMainNav();
                resetContentVisual();
                resetAccordion();
            }
        }).trigger('resize');

        /* ==========================================================================
         EVENTS
         ========================================================================== */

        // Gestion du menu principal sur mobile
        $document.on('click', '#menu-btn', function (event) {
            event.preventDefault();
            $body.toggleClass('main-menu-opened');
        }).on('click', function(event){
            var $target = $(event.target);
            if($body.hasClass('main-menu-opened') && $target.parents('#header').length < 1){
                $body.removeClass('main-menu-opened');
            }

            // Gestion des accordéons en mode mobile
        }).on('click', '.section-title', function (event) {
            if($window.width() < RESOLUTION_DESKTOP){
                event.preventDefault();
                $(this).parents('.section-link').toggleClass('opened');
            }

            // Gestion de l'affichage des modales
        }).on('click', '.modal-trigger', function (event) {
            $body.addClass('modal-active');

        }).on('click', '.close-modal-link', function(event){
            $body.removeClass('modal-active');
            $('.modal-content').html("");
            $('.error').hide();
            signature.clear();

            // Gestion du rappatriement du contenu AJAX
        }).on('submit', '#get-message-form', function (event) {
            event.preventDefault();
            getAsyncMessage();

            // Gestion de l'iframe contenant la signature
        }).on('click', '#show-signature-link', function(event){
            signature.show();
        }).on('click', '#clear-signature-link', function(event){
            signature.clear();
        });

    });

    /* ==========================================================================
     FUNCTIONS
     ========================================================================== */

    // Réinitialise la vue de la navigation pour résolution de bureau
    function resetMainNav(){
        $body.removeClass('main-menu-opened');
    }

    // Dimensionnement des images du bloc section du milieu
    function resetContentVisual(){
        $('.content-visual').each(function(){
            var $this = $(this);
            $this.css({'height' : $this.parents('.content-item').height() + "px"});
        });
    }

    // Suppression des accordéons en vue bureau
    function resetAccordion(){
        $('.section-link.opened').each(function(){
            $(this).removeClass('opened');
        });
    }

    // Déplacement du titre de l'article mis en avant (fond de couleur) en fonction de la résolution
    function resetFocusArticle() {
        if($window.width() < RESOLUTION_DESKTOP && $focusArticle.hasClass('large-mode')){
            $('.focus-title').remove();
            $focusArticle.removeClass('large-mode').prepend($focusContentTitle);
        }else if ($window.width() > (RESOLUTION_DESKTOP - 1) && !$focusArticle.hasClass('large-mode')){
            $focusArticle.addClass('large-mode').find('.focus-title').remove();
            $focusArticle.find('.content-infos').prepend($focusContentTitle);
        }
    }

    // Récupération et ajout au conteneur des données liées à l'appel AJAX
    function getAsyncMessage(){

        if($('#name').val().length < 1){
            $('#error-name').show();
            return;
        }

        var $form = $('#get-message-form');
        $('#signature-input').val(signature.getSignatureData());
        $.ajax({
            url: $form.attr('action'),
            type: "POST",
            data: $form.serialize()
        }).done(function(message) {
            $('.modal-content').html(message);
        }).fail(function() {
            console.error("An error has occured...")
        });
        $('.error').hide();
    }

    jQuery.fn[sr] = function(fn){  return fn ? this.bind('resize', debounce(fn)) : this.trigger(sr); };
})(jQuery,'smartresize');