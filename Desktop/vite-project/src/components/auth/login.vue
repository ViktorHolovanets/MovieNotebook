<template>
    <div class="section">
        <div class="container">
            <div class="row full-height justify-content-center">
                <div class="col-12 text-center align-self-center py-5">
                    <div class="section pb-5 pt-5 pt-sm-2 text-center">
                        <h6 class="mb-0 pb-3"><span v-bind:class="{ 'text-danger': isActive }">Log In </span><span
                                v-bind:class="{ 'text-danger': !isActive }">Sign Up</span></h6>
                        <input class="checkbox" type="checkbox" id="reg-log" name="reg-log" hidden="hidden"
                               v-on:change="toggleActive"/>
                        <label for="reg-log"></label>
                        <div class="card-3d-wrap mx-auto col-12 col-sm-10 col-md-6">
                            <div class="card-3d-wrapper">
                                <div class="card-front">
                                    <div class="center-wrap">
                                        <div class="section text-center">
                                            <div class="form-group">
                                                <input type="email" class="form-style" placeholder="Your Email"
                                                       v-model="login_email">
                                            </div>
                                            <div class="form-group mt-2">
                                                <input type="password" class="form-style" placeholder="Your Password"
                                                       v-model="login_password">
                                            </div>
                                            <a class="btn m-4 btn-success" v-on:click="handleFormSubmit">Submit</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-back">
                                    <div class="center-wrap">
                                        <div class="section text-center">
                                            <div class="form-group">
                                                <input type="text" class="form-style" placeholder="Your Name"
                                                       v-model="register_name">
                                            </div>
                                            <div class="form-group mt-2">
                                                <input type="email" class="form-style" placeholder="Your Email"
                                                       v-model="register_email">
                                            </div>
                                            <div class="form-group mt-2">
                                                <input type="password" name="logpass" class="form-style"
                                                       placeholder="Your Password"
                                                       v-model="register_password">
                                            </div>
                                            <div class="form-group mt-2">
                                                <input type="password" class="form-style" placeholder="Confirm Password"
                                                       v-model="register_confirmPassword">
                                            </div>
                                            <a class="btn m-4 btn-success" v-on:click="handleFormSubmit">Submit</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {_register, _login, _getViews} from "../../services/http/httpRequestToServer"
import {mapActions} from "vuex";
import router from "../../router";

export default {
    name: "login",
    data() {
        return {
            isActive: true,
            register_name: '',
            register_password: '',
            register_confirmPassword: '',
            register_email: '',
            login_email: '',
            login_password: '',
        };
    },
    computed: {},
    methods: {
        ...mapActions(['updateToken']),
        toggleActive() {
            this.isActive = !this.isActive;
        },
        async handleFormSubmit() {
            let dataRequest;
            if (this.isActive) {
                dataRequest = {
                    email: this.login_email,
                    password: this.login_password
                };
            } else {
                dataRequest = {
                    UserName: this.register_name,
                    Email: this.register_email,
                    Password: this.register_password,
                    ConfirmPassword: this.register_confirmPassword
                };
            }
            if (!this.validateFields(dataRequest)) {
                alert('Please fill in all fields.');
                return;
            }
            try {
                const data = this.isActive ? await _login(dataRequest) : await _register(dataRequest);
                if(data.token){
                    await this.updateToken(data.token);
                    localStorage.setItem('token', data.token);
                    await _getViews();
                    await router.push({ name: 'home', params: { type: 'views' } })
                }
            } catch (error) {
                console.error(error);
            }
        },
        validateFields(dataRequest) {
            for (let key in dataRequest) {
                if (!dataRequest[key]) {
                    return false;
                }
            }
            return true;
        }
    }
    ,
}
</script>

<style scoped>

p {
    font-weight: 500;
    font-size: 14px;
    line-height: 1.7;
}

h6 span {
    padding: 0 20px;
    text-transform: uppercase;
    font-weight: 700;
}

.section {
    position: relative;
    width: 100%;
    display: block;
}


.checkbox:checked + label,
.checkbox:not(:checked) + label {
    position: relative;
    display: block;
    text-align: center;
    width: 60px;
    height: 16px;
    border-radius: 8px;
    padding: 0;
    margin: 10px auto;
    cursor: pointer;
    background-color: #ffeba7;
}

.checkbox:checked + label:before,
.checkbox:not(:checked) + label:before {
    position: absolute;
    display: block;
    width: 36px;
    height: 36px;
    border-radius: 50%;
    color: #ffeba7;
    background-color: #102770;
    content: '♪';
    z-index: 20;
    top: -10px;
    left: -10px;
    line-height: 36px;
    text-align: center;
    font-size: 24px;
    transition: all 0.5s ease;
}

.checkbox:checked + label:before {
    transform: translateX(44px) rotate(-360deg);
}


.card-3d-wrap {
    position: relative;
    max-width: 100%;
    height: 400px;
    -webkit-transform-style: preserve-3d;
    transform-style: preserve-3d;
    perspective: 800px;
    margin-top: 60px;
}

.card-3d-wrapper {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    -webkit-transform-style: preserve-3d;
    transform-style: preserve-3d;
    transition: all 600ms ease-out;
}

.card-front, .card-back {
    width: 100%;
    height: 100%;
    /*background-color: rgba(75, 7, 75, 70%);*/
    background: url(../../assets/images/fon.gif);
    overflow: hidden;
    position: absolute;
    border-radius: 6px;
    left: 0;
    top: 0;
    -webkit-transform-style: preserve-3d;
    transform-style: preserve-3d;
    -webkit-backface-visibility: hidden;
    -moz-backface-visibility: hidden;
    -o-backface-visibility: hidden;
    backface-visibility: hidden;
}

.card-back {
    transform: rotateY(180deg);
}

.checkbox:checked ~ .card-3d-wrap .card-3d-wrapper {
    transform: rotateY(180deg);
}

.center-wrap {
    position: absolute;
    width: 100%;
    padding: 0 35px;
    top: 50%;
    left: 0;
    transform: translate3d(0, -50%, 35px) perspective(100px);
    z-index: 20;
    display: block;
}


.form-group {
    position: relative;
    display: block;
    margin: 0;
    padding: 0;
}

.form-style {
    padding: 13px 20px;
    width: 100%;
    font-weight: 500;
    border-radius: 4px;
    font-size: 14px;
    line-height: 22px;
    letter-spacing: 1px;
    outline: none;
    color: #c4c3ca;
    background-color: #1f2029;
    border: none;
    -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
    box-shadow: 0 4px 8px 0 rgba(21, 21, 21, .2);
}

</style>
