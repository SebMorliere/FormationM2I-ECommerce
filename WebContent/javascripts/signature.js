var signature = {

    svg: document.getElementsByTagName('iframe')[0].contentWindow,
    pathdata: document.getElementById('signatureData'),

    getSignatureData: function(){
        return this.svg.getSignature();
    },

    show: function(){
        this.pathdata.textContent = this.getSignatureData();
    },

    clear: function(){
        this.svg.clearSignature();
        this.pathdata.textContent = '';
    }
}

