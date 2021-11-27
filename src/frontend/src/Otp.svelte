<script>
    
</script>

<div class = "login">
    
    <div class = "title">
        <h1>ParkMyCar <i class="fas fa-car-side"></i>
        </h1>  
    </div>

    <div class = "format">
        <h1>Please enter the OTP sent to your registered email ID to complete the verification process:</h1>
        <br>
        <div class = "box">
            <div class = "otp_input">
            <input type="password" name="otp_input">
            </div>
            <br><br>
            <div class = "submit-button">
            <button type = "submit" class = "submit-button"><b>Verify</b></button>
            </div>
        </div>
    </div>
</div>

<style>
    input{
        margin: 5px;
        text-align: center;
    }
    *{
        padding:0%;
        margin: 0;
        box-sizing: border-box;

    }
 
    .login{
        display: flex;
        align-items: center;
        justify-content: space-around;
        height: 100vh;
        flex-direction: column;
    }

    .box{
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }

    h1{
        text-align: center;
    }

    .title{
        font-size: 45px;
        color: #333;
    }
    
    .format{
        background-color: #333 !important;
        padding: 40px;
        border-radius: 20px;
    }

    button{
        background-color: lawngreen;
        border-color: #333;
        padding: 5px;
    }

    .submit-button{
        width: 206px;
    }
</style>