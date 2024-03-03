function sendData(form, method, url, jwt_token) {
    const FD = new FormData(form);
    console.log(FD);
    const XHR = new XMLHttpRequest();
    XHR.open(method, url);
    XHR.onreadystatechange = function() {
        if (XHR.readyState == XMLHttpRequest.DONE) {
            window.history.go(-1);
        }
    }
    XHR.setRequestHeader(
        "Authorization",
        "Bearer " + jwt_token,
    );
    XHR.send(FD);
}

let forms = document.getElementsByTagName('form');

for(let i = 0; i < forms.length; i++) {
    console.log(forms[i]);
    console.log(jwt_token);
    forms[i].addEventListener('submit', function(event){
        // console.log('Form submit event detected.');
        const action = this.getAttribute("action");
        if(typeof(action)==="undefined"){
            return;
        }
        event.preventDefault();
        // sendData(this, "POST", action, jwt_token);
        sendData(this, "POST", action, "jwt_token");
    }, false);
}
