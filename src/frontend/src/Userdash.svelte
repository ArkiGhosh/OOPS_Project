<script>
import {Router, navigate} from "svelte-navigator";
let fl = false; 
let fll = false;
let arr = [
    {name:"6A",cost:"12$"},
    {name:"69A",cost:"12$"},
    {name:"67A",cost:"12$"},
]
let brr = [
    false,false,false
]




</script>

<main>



    <h1>Jojo</h1>


    <form on:submit|preventDefault={function(){fl=true}}>

    <label for="#">Location</label>
    <input type="text">
    <label for ="#">Date</label>
    <input type="text">
    <label for="#">In Time</label>
    <input type="text">
    <label for="#">Out Time</label>
    <input type="text">
   <br> 
    <button>Search</button>
    </form>


    {#if fl==true}

        {#each arr as {name,cost},i}
            <div class="box">
                <a href="#" on:click="{function(){brr[i]=true}}"><h3>{name}</h3></a>
                <p>{cost}</p>
                {#if brr[i]==true}
                    <h4>Maxtime     69</h4>
                    <h4>cost     69</h4>
                    <h4>Services     69</h4>
                    <h4>Rating     69</h4>
                {/if}
            </div>
        {/each}


    {/if}

</main>


<style>


main{
    color: black;
    background-color: blanchedalmond;
    text-align: center;
}

.box {
		width: 300px;
		border: 1px solid #aaa;
		border-radius: 2px;
		box-shadow: 2px 2px 8px rgba(0,0,0,0.1);
		padding: 1em;
		margin: auto;
        margin-bottom: 1em;
        text-align: center;
        position:relative;
	}

</style>

 