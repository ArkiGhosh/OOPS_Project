<script>
	import Home from "./Home.svelte"; 
	import Signup from "./Signup.svelte";
	import Otp from "./Otp.svelte";
	import Userdash from "./Userdash.svelte";
	import {Router, Route} from "svelte-navigator";
import Profile from "./Profile.svelte";
</script>

<Router>
<main>
	<!--<Home />-->
	<Route path = "/"><Home /></Route>
	<Route path = "/Signup"><Signup /></Route>
	<Route path = "/Otp"><Otp /></Route>
	<Route path = "/Userdash"><Userdash /></Route>
	<Route path = "/Profile"><Profile /></Route>

</main>
</Router>
	
<style>
	
	main{
		display: flex;
		flex-direction: column;
	}

	/*h1{
		font-size: 72px;
		text-align: center;
	}

	h3{
		font-size: 38px;
	}*/
</style>