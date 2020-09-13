<template>
    <div class="container">
        <div class="row justify-content-center">
            <div class="register-form">
                <div class="logo-wrapper">
                    <img class="logo" src="/static/images/logo.png">
                    <div class="tagline">Open source task management tool</div>
                </div>
                <form>
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username">
                    </div>
                    <div class="form-group">
                        <label for="emailAddress">Email address</label>
                        <input type="email" class="form-control" id="eamilAddress">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password">
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Create account</button>
                    <p class="accept-terms text-center text-muted">
                        By clicking "Create account", you agree to our 
                        <!-- 서비스 방침, 개인정보 보호정책 관련 내용 만들게되면 링크 -->
                        <a href="#">terms of service</a> and 
                        <a href="#">privacy policy</a>.
                    </p>
                    <p class="text-center text-muted">
                        Already have an account?
                        <a href="/login"> Sign in </a>
                    </p>
                </form>
            </div>
        </div>
        <div class="footer">
            <span class="copyright">&copy; 2018 motiveko.com</span>
            <ul class="footer-links list-inline float-right">
                <!-- footer lists -->
                <li class="list-inline-item"><a href="#">About</a></li>
                <li class="list-inline-item"><a href="#">Terms of Service</a></li>
                <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
                <li class="list-inline-item"><a href="https://github.com/motiveko/taskagile">GitHub</a></li>

            </ul>
        </div>
    </div>  
</template>

<script>
export default {
    name: "RegisterPage"
}
</script>
<style lang="scss" scoped>
    .container {
        max-width: 900px;
    }
    .register-form {
        margin-top: 50px;
        max-width: 320px;
    }
    .logo-wrapper {
        text-align: center;
        margin-bottom: 40px;

        .tagline {
            line-height: 180%;
            color: #666;
        }
        .logo {
            max-width: 150px;
            margin: 0 auto;
        }
    }
    .footer{
        width: 100%;
        font-size: 13px;
        color: #666;
        line-height: 40px;
        border-top: 1px solid #ddd;
        margin-top: 50px;

        .list-inline-item{
            margin-right: 10px;
        }
        a{
            color: #666;
        }
    } 

</style>