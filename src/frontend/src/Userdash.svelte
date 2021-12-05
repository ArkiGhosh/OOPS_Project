<script>
    import { Router, navigate } from "svelte-navigator";
    import {
        Alert,
        Form,
        FormGroup,
        FormText,
        Input,
        Label,
        ListGroup,
        ListGroupItem,
    } from "sveltestrap";
    import { userName, userId } from "./store";
    import { Styles } from "sveltestrap";
    import { Col, Container, Row } from "sveltestrap";
    import {
        Button,
        Card,
        CardBody,
        CardFooter,
        CardHeader,
        CardSubtitle,
        CardText,
        CardTitle,
    } from "sveltestrap";
    import {
        Collapse,
        Navbar,
        NavbarToggler,
        NavbarBrand,
        Nav,
        NavItem,
        NavLink,
        Dropdown,
        DropdownToggle,
        DropdownMenu,
        DropdownItem,
    } from "sveltestrap";
    import { bind, each, fix_and_outro_and_destroy_block, space } from "svelte/internal";
 
    let isOpen = false;
 
    function handleUpdate(event) {
        isOpen = event.detail.isOpen;
    }
    let fl = false;
    let fll = false;
    let arr = [];
    var brr = {};
    var frr = {};
    var bools = [];
    //Dec 3
    let bookings;
 
    let loc;
    let date, intime, outtime;
    let cost;
    let waiting_list_vis = [];
    let waiting_list_list = {};
    let booking_flags = [];
    let dry_clean = [];
    let car_wash = [];
    let tyre_fill = [];
    let no_balance = false;
    let worker_name = [];
    let worker_rating = [];
    let feedback = [];
    let services = ["Car Wash"];
 
    $: cost = 25 * (outtime - intime);
 
    async function search(e) {
        //display all the slots
        //get all the bookings of that date
        //check if intime and outtime and slot clash with some booking, if yes color red
        //else color green
        //if red, add booking to waiting table instead
        //if a booking is cancled go through the booking table and if the slot is free in waiting table add it to booking table and remove from waiting
        //add booking to user collumn
        //make a user waiting column?
 
        let s = "http://localhost:8080/slots";
 
        const res = await fetch(s, {
            method: "GET",
            headers: {
                "Content-type": "application/json",
                Accept: "application/json",
                "Access-Control-Allow-Origin": "http://localhost:8080",
            },
        });
 
        const slots = await res.json();
        console.log(date);
 
        s = "http://localhost:8080/bookings/space/" + loc + "/date/" + date;
 
        const res2 = await fetch(s, {
            method: "GET",
            headers: {
                "Content-type": "application/json",
                Accept: "application/json",
                "Access-Control-Allow-Origin": "http://localhost:8080",
            },
        });
 
        bookings = await res2.json();
 
        arr = slots;
 
        for (let i = 0; i < arr.length; i++) {
            brr[arr[i].slotnum] = "success";
            frr[arr[i].slotnum] = "1hr";
            bools[i] = false;
            waiting_list_vis[i] = false;
            booking_flags[i] = false;
            car_wash[i] = false;
            tyre_fill[i] = false;
            dry_clean[i] = false;
            feedback[i] = false;
        }
 
        console.log(bookings);
 
        for (let i = 0; i < bookings.length; i++) {
            let boo = bookings[i];
 
            if (brr[boo.slotid] == "success") {
                console.log("here");
                if (!(boo.outtime < intime || outtime < boo.intime)) {
                    brr[boo.slotid] = "danger";
                    frr[boo.slotid] = boo.outtime - intime;
                    if (boo.outtime == intime || boo.intime == outtime)
                        brr[boo.slotid] = "success";
                }
            }
        }
 
        console.log(brr);
    }
 
    async function details(i, b, slotn) {
        bools[i] = bools[i] ^ 1;
 
        //show services and worker name and rating
 
        let s = "http://localhost:8080/get_worker_by_slot/" + slotn;
 
        const res2 = await fetch(s, {
            method: "GET",
            headers: {
                "Content-type": "application/json",
                Accept: "application/json",
                "Access-Control-Allow-Origin": "http://localhost:8080",
            },
        });
 
        let wor = await res2.json();
 
        console.log(wor);
 
        worker_name[i] = wor.name;
        worker_rating[i] = wor.avg_rating;
 
        //waiting list
    }
 
    async function wating_list(slotn, j) {
        waiting_list_vis[j] = !waiting_list_vis[j];
 
        for (let i = 0; i < bookings.length; i++) {
            let boo = bookings[i];
            if (boo.slotid == slotn) {
                let s =
                    "http://localhost:8080/bookings/update/" +
                    boo.id +
                    "/" +
                    $userName;
                let lis = [];
                lis = boo.users;
                console.log(lis);
                lis.push($userName);
                const data = {};
                data["users"] = lis;
 
                const res = await fetch(s, {
                    method: "GET",
                    headers: {
                        "Content-type": "application/json",
                        Accept: "application/json",
                        "Access-Control-Allow-Origin": "http://localhost:8080",
                    },
                });
 
                waiting_list_list[j] = lis;
                console.log(waiting_list_list);
 
                var li = document.createElement("ul");
                var ss = "";
                for (let i = 0; i < lis.length; i++) {
                    console.log("=========");
 
                    console.log(lis[i]);
 
                    ss += "<li>";
                    ss += lis[i];
                    ss += "</li>";
                }
 
                li.innerHTML = ss;
                document.getElementById(j).appendChild(li);
            }
        }
    }
 
  
 
    async function newbooking(i, slotn) {
        //add services to booking table
        //add booking id to user and worker table
        //check if balance is enough
        let ser = [];
        let cosst = 25 * (outtime - intime);
        if (dry_clean[i]) {
            ser.push("Dry Clean");
        }
        if (car_wash[i]) {
            cosst += 15;
            ser.push("Car Wash");
        }
        if (tyre_fill[i]) {
            cosst += 5;
            ser.push("Fill Tyres");
        }
 
        let s = "http://localhost:8080/pay/" + $userId + "/" + cosst;
 
        const res = await fetch(s, {
            method: "GET",
            headers: {
                "Content-type": "application/json",
                Accept: "application/json",
                "Access-Control-Allow-Origin": "http://localhost:8080",
            },
        });
 
        const proceed = await res.json();
        console.log(proceed);
        if (proceed["success"] == true) {
            let s = "http://localhost:8080/booking";
 
            let data = {};
 
            data["id"] = 3;
            data["slotid"] = slotn;
            data["space"] = loc;
            data["cost"] = cost;
            data["intime"] = intime;
            data["outtime"] = outtime;
            data["date"] = date;
            data["users"] = [$userName];
            data["services"] = ser;
 
            const res = await fetch(s, {
                method: "POST",
                headers: {
                    "Content-type": "application/json",
                    Accept: "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080",
                },
                body: JSON.stringify(data),
            });
 
            let bo = await res.json();
 
            console.log(bo);
            console.log(bo.id);
 
            //adds booking id to worker
            s = "http://localhost:8080/worker/update/" + slotn + "/" + bo.id;
 
            const res2 = await fetch(s, {
                method: "GET",
                headers: {
                    "Content-type": "application/json",
                    Accept: "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080",
                },
            });
 
            s = "http://localhost:8080/user/update/" + $userId + "/" + bo.id;
 
            const res3 = await fetch(s, {
                method: "GET",
                headers: {
                    "Content-type": "application/json",
                    Accept: "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080",
                },
            });
 
            no_balance = false;
            feedback[i] = true;
        } else {
            //Add error
            no_balance = true;
        }
    }
 
    let rating,comment
    async function feed(i,rat,comm,slotn){
 
        worker_rating[i] = rat
 
        let data = {}
        data['rating'] =rat
        data['comment'] = comm
        
        let s = "http://localhost:8080/feedback/"+slotn
        const res = await fetch(s, {
                method: "POST",
                headers: {
                    "Content-type": "application/json",
                    Accept: "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080",
                },
                body: JSON.stringify(data),
            });
        
            let d = await res.json()
            console.log(d.rating)
        worker_rating[i] = d.rating
    }
 
    $:{
        services = services;
        cost = (outtime-intime)*25
        console.log(outtime)
        console.log(intime)
        console.log(outtime-intime)
        console.log((outtime-intime)*25)
        console.log(cost)
      
 
 
        for(let i =0;i<car_wash.length;i++){
            if(car_wash[i]){
                cost += 15
                break;
            }
        }
        for(let i =0;i<tyre_fill.length;i++){
            if(tyre_fill[i]){
                cost += 5
                break;
            }
        }
 
      
    }
    

    
    let checker = false;
 
    console.log($userName);
    async function func_check(){
        const res = await fetch(s, {
            method: "GET",
            headers: {
                "Content-type": "application/json",
                Accept: "application/json",
                "Access-Control-Allow-Origin": "http://localhost:8080",
            },
        });
 
        const proceed = await res.json();
        console.log(proceed);
        if (proceed["success"] == true){
            checker = true;
        }
    }
 
    console.log($userName);
</script>
 
<div class="hello">
    <Navbar class="rounded-3" color="dark" dark expand="md">
        <div class="name">
            <NavbarBrand href="/"
                >ParkMyCar <i class="fas fa-car-side" /></NavbarBrand
            >
        </div>
        <NavbarToggler on:click={() => (isOpen = !isOpen)} />
        <Collapse {isOpen} navbar expand="md" on:update={handleUpdate}>
            <Nav class="ms-auto" navbar>
                <div class="form-group" style="display:inline-block">
                    <form class="navbar-form navbar-left" role="search">
                        <div class="searchbar-container">
                            <div class="searchbar">
                                <input
                                    type="text"
                                    class="form-control"
                                    placeholder="Search"
                                />
                            </div>
                            <div class="searchicon">
                                <button type="submit" class="btn_btn-default">
                                    <span class="glyphicon glyphicon-search"
                                        ><i class="fa fa-search" /></span
                                    >
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <NavItem>
                    <NavLink href="https://github.com/an-bhv/carp"
                        >GitHub</NavLink
                    >
                </NavItem>
                <Dropdown nav inNavbar>
                    <DropdownToggle nav caret>Options</DropdownToggle>
                    <DropdownMenu end>
                        <DropdownItem
                            ><a href="#" on:click={() => navigate("Profile")}
                                >Profile</a
                            ></DropdownItem
                        >
                        <DropdownItem
                            ><a href="/"
                                >Log Out</a
                            ></DropdownItem
                        >
                    </DropdownMenu>
                </Dropdown>
            </Nav>
        </Collapse>
    </Navbar>
</div>
 
<br /><br />
 
<div class="mybox">
    <h1>Hello, {$userName}!</h1>
    <form
        on:submit|preventDefault={function () {
            fl = true;
        }}
    >
        <FormGroup>
            <Label for="#">Location:</Label>
 
            <Input type="select" bind:value={loc}>
                <option value="A" selected> A </option>
                <option value="B"> B </option>
            </Input>
        </FormGroup>
        <FormGroup>
            <Label for="#">Date:</Label>
            <Input type="date" required bind:value={date} />
        </FormGroup>
        <FormGroup>
            <Container>
                <Row cols={2}>
                    <div class="" style="display:inline-block">
                        <Col>
                            <Card
                                style="background-color:#333;border-color:#333;width:8rem"
                            >
                                <Label for="#">In Time:</Label>
                                <Input
                                    type="select"
                                    style="width: 6rem;"
                                    bind:value={intime}
                                >
                                    {#each Array(24) as _, i}
                                        <option value={i}>{i}</option>
                                    {/each}
                                </Input>
                            </Card>
                        </Col>
                        <Col>
                            <Card
                                style="background-color:#333;border-color:#333;width:8rem"
                            >
                                <Label for="#">Out Time:</Label>
 
                                <Input
                                    type="select"
                                    style="width: 6rem;"
                                    bind:value={outtime}
                                >
                                    {#each Array(24) as _, i}
                                        <option value={i}>{i}</option>
                                    {/each}
                                </Input>
                            </Card>
                        </Col>
                    </div>
                </Row>
            </Container>
        </FormGroup>
        <button class="search" type="submit" on:click={search}
            ><b>Search</b></button
        >
    </form>
</div>
<br /><br />
 
{#if fl == true}
    <Container>
        <Row cols={3} style="margin-bottom: 5px;">
            {#each arr as a, i}
                <Col style="margin-bottom: 10px;">
                    <div class="search-container">
                        <Card
                            class="slots"
                            body
                            color={brr[a.slotnum]}
                            style="width: 18rem;display:inline-block;"
                        >
                            <CardHeader
                                on:click={() =>
                                    details(i, brr[a.slotnum], a.slotnum)}
                            >
                                <CardTitle><h3>{a.slotnum}</h3></CardTitle>
                            </CardHeader>
 
                            {#if bools[i]}
                                <h4 style="margin-top: 2em;">
                                    Cost = {cost}Rs.
                                </h4>
 
                                <p>Maxtime = {outtime - intime}</p>
 
                                {#if brr[a.slotnum] == "danger"}
                                    <p>
                                        Free after {frr[a.slotnum]}hr(s) of
                                        check-in
                                    </p>
 
                                    <Button
                                        color="warning"
                                        on:click={() =>
                                            wating_list(a.slotnum, i)}
                                        >Join waiting list</Button
                                    >
 
                                    <div id={i} />
                                {:else}
                                    <br />
                                    <Button
                                        color="secondary"
                                        on:click={() => {
                                            booking_flags[i] =
                                                !booking_flags[i];
                                        }}>BOOK</Button
                                    >
 
                                    {#if booking_flags[i]}
                                        <Input
                                            type="checkbox"
                                            bind:checked={dry_clean[i]}
                                            label="Dry Clean"
                                            bind:group = {services}
                                            value= "Dry Clean"
                                        />
                                        <Input
                                            type="checkbox"
                                            bind:checked={car_wash[i]}
                                            label="Car Wash"
                                            bind:group = {services}
                                            value="Car Wash"
                                        />
                                        <Input
                                            type="checkbox"
                                            bind:checked={tyre_fill[i]}
                                            label="Fill Tyres"
                                            bind:group = {services}
                                            value="Fill Tyres"
                                        />
 
                                        {#if !feedback[i]}
                                            <Button
                                                color="primary"
                                                on:click={() => {
                                                    newbooking(i, a.slotnum);
                                                }}>Pay</Button
                                            >
                                            
                                            <Button 
                                                style = "background-color:#333;color:white"
                                                on:click={() => {
                                                    func_check();
                                                }}>Apply Prome Code</Button>
                                            {#if checker == false}
                                            <p style = "color:#333">You are not eligible for promo code.</p>
                                            {:else}
                                            Enter the promo code:<Input type = "text" /> 
                                            <button style = "background-color:#333;color:lawngreen"
                                            on:click={() => {
                                                promochecking();
                                            }}>Enter</button>
                                            {/if}


                                        {:else}
                                            <Alert color="info"
                                                >Booking Done successfully</Alert
                                            >
                                        {/if}
 
                                        {#if no_balance}
                                            <Alert color="danger" dismissible
                                                >Not enough balance in wallet</Alert
                                            >
                                        {/if}
                                    {/if}
                                {/if}
                                <CardFooter>
                                    Worker Name: {worker_name[i]}<br />
                                    Rating : {worker_rating[i]}/5
 
                                    {#if feedback[i]}
                                        <Label for="rating">Rate {worker_name[i]}</Label>
 
                                        <Input
                                            type="range"
                                            name="range"
                                            id="exampleRange"
                                            min={0}
                                            max={5}
                                            step={0.5}
                                            placeholder="Range placeholder"
                                            bind:value = {rating}
 
                                        />
 
                                        {rating}/5
 
                                        <Label for="comment">Comment</Label>
                                        <Input type="textarea" bind:value={comment}/>
                                        <Button color ="secondary" on:click={()=>feed(i,rating,comment,a.slotnum)}>Rate</Button>
                                    {/if}
                                </CardFooter>
                            {/if}
                        </Card>
                    </div>
                </Col>
            {/each}
        </Row>
    </Container>
{/if}
 
<style>
    * {
        display: flex;
        flex-direction: column;
 
        justify-content: center;
    }
    .searchbar-container {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        border-radius: 5px;
    }
 
    .search {
        border-color: #333 !important;
        color: lawngreen;
        background-color: #333;
        text-align: center !important;
    }
 
    span {
        color: lawngreen;
        background-color: #212529;
    }
 
    button {
        background-color: lawngreen !important;
        color: #333 !important;
        border-radius: 5px;
    }
 
    .mybox {
        background-color: #333 !important;
        padding: 40px;
        border-radius: 20px;
        display: flex;
        align-items: center;
        justify-content: center;
        align-self: center;
        width: 400px;
        flex-direction: column;
    }
 
    input {
        height: 5vh;
    }
 
    .searchicon {
        margin-left: 5px;
        margin-right: 5px;
        background-color: #212529;
    }
 
    .btn_btn-default {
        height: 30px;
        width: 30px;
        background-color: #212529 !important;
        border-color: #212529 !important;
    }
 
    .hello {
        display: inline-block;
        border-radius: 5px;
    }
 
    .name {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        color: lawngreen;
    }
</style>
