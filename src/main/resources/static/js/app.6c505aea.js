(function(e){function t(t){for(var s,o,i=t[0],m=t[1],l=t[2],c=0,d=[];c<i.length;c++)o=i[c],Object.prototype.hasOwnProperty.call(a,o)&&a[o]&&d.push(a[o][0]),a[o]=0;for(s in m)Object.prototype.hasOwnProperty.call(m,s)&&(e[s]=m[s]);u&&u(t);while(d.length)d.shift()();return n.push.apply(n,l||[]),r()}function r(){for(var e,t=0;t<n.length;t++){for(var r=n[t],s=!0,i=1;i<r.length;i++){var m=r[i];0!==a[m]&&(s=!1)}s&&(n.splice(t--,1),e=o(o.s=r[0]))}return e}var s={},a={app:0},n=[];function o(t){if(s[t])return s[t].exports;var r=s[t]={i:t,l:!1,exports:{}};return e[t].call(r.exports,r,r.exports,o),r.l=!0,r.exports}o.m=e,o.c=s,o.d=function(e,t,r){o.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:r})},o.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},o.t=function(e,t){if(1&t&&(e=o(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var r=Object.create(null);if(o.r(r),Object.defineProperty(r,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var s in e)o.d(r,s,function(t){return e[t]}.bind(null,s));return r},o.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return o.d(t,"a",t),t},o.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},o.p="/";var i=window["webpackJsonp"]=window["webpackJsonp"]||[],m=i.push.bind(i);i.push=t,i=i.slice();for(var l=0;l<i.length;l++)t(i[l]);var u=m;n.push(["56d7","chunk-vendors"]),r()})({"0783":function(e,t,r){"use strict";var s=r("3270"),a=r.n(s);a.a},"0970":function(e,t,r){},3270:function(e,t,r){},"32d6":function(e,t,r){},"3be7":function(e,t,r){"use strict";var s=r("0970"),a=r.n(s);a.a},"4b62":function(e,t,r){},"56d7":function(e,t,r){"use strict";r.r(t);r("cadf"),r("551c"),r("f751"),r("097d");var s=r("2b0e"),a=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{attrs:{id:"app"}},[r("router-view")],1)},n=[],o={name:"App"},i=o,m=(r("5c0b"),r("2877")),l=Object(m["a"])(i,a,n,!1,null,null,null),u=l.exports,c=r("8c4f"),d=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"container public"},[r("div",{staticClass:"row justify-content-center"},[r("div",{staticClass:"form"},[r("Logo"),r("form",{on:{submit:function(t){return t.preventDefault(),e.submitForm(t)}}},[r("div",{directives:[{name:"show",rawName:"v-show",value:e.errorMessage,expression:"errorMessage"}],staticClass:"alert alert-danger failed"},[e._v(e._s(e.errorMessage))]),r("div",{staticClass:"form-group"},[r("label",{attrs:{for:"username"}},[e._v("Username or email address")]),r("input",{directives:[{name:"model",rawName:"v-model",value:e.form.username,expression:"form.username"}],staticClass:"form-control",attrs:{type:"text",id:"username"},domProps:{value:e.form.username},on:{input:function(t){t.target.composing||e.$set(e.form,"username",t.target.value)}}}),e.$v.form.username.$dirty?r("div",{staticClass:"field-error"},[e.$v.form.username.required?e._e():r("div",{staticClass:"error"},[e._v("Username or email address is required")])]):e._e()]),r("div",{staticClass:"form-group"},[r("label",{attrs:{for:"password"}},[e._v("Password")]),r("input",{directives:[{name:"model",rawName:"v-model",value:e.form.password,expression:"form.password"}],staticClass:"form-control",attrs:{type:"password",id:"password"},domProps:{value:e.form.password},on:{input:function(t){t.target.composing||e.$set(e.form,"password",t.target.value)}}}),e.$v.form.password.$dirty?r("div",{staticClass:"field-error"},[e.$v.form.password.required?e._e():r("div",{staticClass:"error"},[e._v("Password is Required")])]):e._e()]),r("button",{staticClass:"btn btn-primary btn-block",attrs:{type:"submit"}},[e._v("Sign in")]),e._m(0)])],1)]),r("PageFooter")],1)},f=[function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"links"},[r("p",{staticClass:"sign-up text-muted"},[e._v("\n                        Don't have an account yet?\n                        "),r("a",{staticClass:"link-sign-up",attrs:{href:"/register"}},[e._v("\n                            Sign Up here\n                        ")])]),r("p",[r("a",{attrs:{href:"#"}},[e._v("Forgot your password?")])])])}],v=r("b5ae"),p=function(){var e=this,t=e.$createElement;e._self._c;return e._m(0)},g=[function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"logo-wrapper"},[r("img",{staticClass:"logo",attrs:{src:"/static/images/logo.png"}}),r("div",{staticClass:"tagline"},[e._v("Open source task management tool")])])}],h={name:"Logo"},_=h,b=(r("0783"),Object(m["a"])(_,p,g,!1,null,"5e8de182",null)),w=b.exports,$=function(){var e=this,t=e.$createElement;e._self._c;return e._m(0)},C=[function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("footer",{staticClass:"footer"},[r("span",{staticClass:"copyright"},[e._v("© 2020 motiveko")]),r("ul",{staticClass:"footer-links list-inline float-right"},[r("li",{staticClass:"list-inline-item"},[r("a",{attrs:{href:"#"}},[e._v("About")])]),r("li",{staticClass:"list-inline-item"},[r("a",{attrs:{href:"#"}},[e._v("Terms of Service")])]),r("li",{staticClass:"list-inline-item"},[r("a",{attrs:{href:"#"}},[e._v("Privacy Policy")])]),r("li",{staticClass:"list-inline-item"},[r("a",{attrs:{href:"https://github.com/taskagile/vuejs.spring-boot.mysql",target:"_blank"}},[e._v("GitHub")])])])])}],y={name:"PageFooter"},x=y,P=(r("3be7"),Object(m["a"])(x,$,C,!1,null,"3612961b",null)),L=P.exports,j=r("bc3a"),O=r.n(j),q={authenticate:function(e){return new Promise((function(t,r){O.a.post("/authentications",e).then((function(e){var r=e.data;t(r)})).catch((function(e){r(e)}))}))}},A={name:"LoginPage",data:function(){return{form:{username:"",password:""},errorMessage:""}},components:{Logo:w,PageFooter:L},validations:{form:{username:{required:v["required"]},password:{required:v["required"]}}},methods:{submitForm:function(){var e=this;this.$v.$touch(),this.$v.$invalid||q.authenticate(this.form).then((function(){e.$router.push({name:"HomePage"})})).catch((function(t){e.errorMessage=t.message}))}}},M=A,k=(r("e551"),Object(m["a"])(M,d,f,!1,null,"64dcd4d2",null)),E=k.exports,F=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"container public"},[r("div",{staticClass:"row justify-content-center align-items-center"},[r("div",{staticClass:"form"},[r("Logo"),r("form",{on:{submit:function(t){return t.preventDefault(),e.submitForm(t)}}},[r("div",{directives:[{name:"show",rawName:"v-show",value:e.errorMessage,expression:"errorMessage"}],staticClass:"alert alert-danger failed"},[e._v(e._s(e.errorMessage))]),r("div",{staticClass:"form-group"},[r("label",{attrs:{for:"username"}},[e._v("Username")]),r("input",{directives:[{name:"model",rawName:"v-model",value:e.form.username,expression:"form.username"}],staticClass:"form-control",attrs:{type:"text",id:"username"},domProps:{value:e.form.username},on:{input:function(t){t.target.composing||e.$set(e.form,"username",t.target.value)}}}),e.$v.form.username.$dirty?r("div",{staticClass:"field-error"},[e.$v.form.username.required?e._e():r("div",{staticClass:"error"},[e._v("Username is required")]),e.$v.form.username.alphaNum?e._e():r("div",{staticClass:"error"},[e._v("Username can only contain letters and numbers")]),e.$v.form.username.minLength?e._e():r("div",{staticClass:"error"},[e._v("Username must have at least "+e._s(e.$v.form.username.$params.minLength.min)+" letters.")]),e.$v.form.username.maxLength?e._e():r("div",{staticClass:"error"},[e._v("Username is too long. It can contains maximium "+e._s(e.$v.form.username.$params.maxLength.max)+" letters.")])]):e._e()]),r("div",{staticClass:"form-group"},[r("label",{attrs:{for:"emailAddress"}},[e._v("Email address")]),r("input",{directives:[{name:"model",rawName:"v-model",value:e.form.emailAddress,expression:"form.emailAddress"}],staticClass:"form-control",attrs:{type:"email",id:"emailAddress"},domProps:{value:e.form.emailAddress},on:{input:function(t){t.target.composing||e.$set(e.form,"emailAddress",t.target.value)}}}),e.$v.form.emailAddress.$dirty?r("div",{staticClass:"field-error"},[e.$v.form.emailAddress.required?e._e():r("div",{staticClass:"error"},[e._v("Email address is required")]),e.$v.form.emailAddress.email?e._e():r("div",{staticClass:"error"},[e._v("This is not a valid email address")]),e.$v.form.emailAddress.maxLength?e._e():r("div",{staticClass:"error"},[e._v("Email address is too long. It can contains maximium "+e._s(e.$v.form.emailAddress.$params.maxLength.max)+" letters.")])]):e._e()]),r("div",{staticClass:"form-group"},[r("label",{attrs:{for:"password"}},[e._v("Password")]),r("input",{directives:[{name:"model",rawName:"v-model",value:e.form.password,expression:"form.password"}],staticClass:"form-control",attrs:{type:"password",id:"password"},domProps:{value:e.form.password},on:{input:function(t){t.target.composing||e.$set(e.form,"password",t.target.value)}}}),e.$v.form.password.$dirty?r("div",{staticClass:"field-error"},[e.$v.form.password.required?e._e():r("div",{staticClass:"error"},[e._v("Password is required")]),e.$v.form.password.minLength?e._e():r("div",{staticClass:"error"},[e._v("Password is too short. It can contains at least "+e._s(e.$v.form.password.$params.minLength.min)+" letters.")]),e.$v.form.password.maxLength?e._e():r("div",{staticClass:"error"},[e._v("Password is too long. It can contains maximium "+e._s(e.$v.form.password.$params.maxLength.max)+" letters.")])]):e._e()]),r("button",{staticClass:"btn btn-primary btn-block",attrs:{type:"submit"}},[e._v("Create account")]),e._m(0),e._m(1)])],1)]),r("PageFooter")],1)},N=[function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("p",{staticClass:"accept-terms text-muted"},[e._v("By clicking “Create account”, you agree to our "),r("a",{attrs:{href:"#"}},[e._v("terms of service")]),e._v(" and "),r("a",{attrs:{href:"#"}},[e._v("privacy policy")]),e._v(".")])},function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("p",{staticClass:"text-center text-muted"},[e._v("Already have an account? "),r("a",{attrs:{href:"/login"}},[e._v("Sign in")])])}],S={register:function(e){return new Promise((function(t,r){O.a.post("/registrations",e).then((function(e){var r=e.data;t(r)})).catch((function(e){r(e)}))}))}},U={name:"RegisterPage",data:function(){return{form:{username:"",emailAddress:"",password:""},errorMessage:""}},components:{Logo:w,PageFooter:L},validations:{form:{username:{required:v["required"],minLength:Object(v["minLength"])(2),maxLength:Object(v["maxLength"])(50),alphaNum:v["alphaNum"]},emailAddress:{required:v["required"],email:v["email"],maxLength:Object(v["maxLength"])(100)},password:{required:v["required"],minLength:Object(v["minLength"])(6),maxLength:Object(v["maxLength"])(30)}}},methods:{submitForm:function(){var e=this;this.$v.$touch(),this.$v.$invalid||S.register(this.form).then((function(){e.$router.push({name:"LoginPage"})})).catch((function(t){e.errorMessage="Failed to register user. "+t.message}))}}},T=U,I=(r("a98b"),Object(m["a"])(T,F,N,!1,null,"42c14a3d",null)),R=I.exports;s["a"].use(c["a"]);var D=new c["a"]({mode:"history",base:"/",routes:[{path:"/login",name:"LoginPage",component:E},{path:"/register",name:"RegisterPage",component:R}]}),H=r("2f62");s["a"].use(H["a"]);var J=new H["a"].Store({state:{},mutations:{},actions:{}}),B=r("1dce"),G=r.n(B);s["a"].config.productionTip=!1,s["a"].use(G.a),new s["a"]({router:D,store:J,render:function(e){return e(u)}}).$mount("#app"),O.a.defaults.baseURL="/api",O.a.defaults.headers.common.Accept="application/json",O.a.interceptors.response.use((function(e){return e}),(function(e){return Promise.reject(e)}))},"5c0b":function(e,t,r){"use strict";var s=r("e332"),a=r.n(s);a.a},a98b:function(e,t,r){"use strict";var s=r("4b62"),a=r.n(s);a.a},e332:function(e,t,r){},e551:function(e,t,r){"use strict";var s=r("32d6"),a=r.n(s);a.a}});
//# sourceMappingURL=app.6c505aea.js.map