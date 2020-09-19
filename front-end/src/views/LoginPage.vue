<template>
    <div class="container public">
        <div class="row justify-content-center">
            <div class="form">  
                <Logo/>
                <form @submit.prevent="submitForm" >
                    <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
                    <div class="form-group">
                        <label for="username">Username or email address</label>
                        <input type="text" class="form-control" id="username" v-model="form.username" />
                        <!-- $v : vuelidate, $dirty : touch됬는가? https://github.com/vuelidate/vuelidate 참고 -->
                        <div class="field-error" v-if="$v.form.username.$dirty">
                            <div class="error" v-if="!$v.form.username.required">Username or email address is required</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" v-model="form.password">
                        <div class="field-error" v-if="$v.form.password.$dirty">
                            <div class="error" v-if="$v.form.password.$dirty">Password is Required</div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Sign in</button>
                    <div class="links">
                        <p class="sign-up text-muted">
                            Don't have an account yet?
                            <a href="/register" class="link-sign-up">
                                Sign Up here
                            </a>
                        </p>
                        <p><a href="#">Forgot your password?</a></p>
                    </div>
                </form>
            </div>
        </div>
        <PageFooter/>
    </div>   
</template>

<script>
import { required } from 'vuelidate/lib/validators'
import Logo from '@/components/Logo.vue'
import PageFooter from '@/components/PageFooter.vue'
import authenticationService from '@/services/authentication'

export default {
    name: 'LoginPage',
    data: function (){
        return {
            form: {
                username: '',
                password: ''
            },
            errorMessage: ''
        }
    },
    components: {
        Logo,
        PageFooter
    },
    validations: {
        form: {
            username: {
                required
            },
            password: {
                required
            }
        }
    },
    methods: {
        submitForm() {
            this.$v.$touch()
            if(this.$v.$invalid){
                return
            }
            authenticationService.authenticate(this.form).then(() => {
                // HompPage로 redirect
                this.$router.push({ name: 'HomePage' })
            }).catch((error) => {
                this.errorMessage = error.message
            })
        }
    }
}

</script>

<style lang="scss" scoped>
.links {
    margin: 30px 0 50px 0;
    text-align: center
}
</style>