    <script>
        import { Router, navigate } from "svelte-navigator";
        import { Form, FormGroup, FormText, Input, Label } from "sveltestrap";
        import { Column, Table } from "sveltestrap";
        import { userName } from "./store";
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
    import { prevent_default } from "svelte/internal";

        let UserRows = [];
        let WorkerRows = [];


        let isOpen = false;
        let u = false;
        let disp = false;
        let disp1 = false;
        let deluser = false;
        function setTrue() {
            u = true;
            console.log(u);
        }
        function handleUpdate(event) {
            isOpen = event.detail.isOpen;
        }
     
        async function deletefooter1() {
            f1 = false;
            f2 = false;
            f3 = true;
            f4 = false;
            disp = false;
            disp1 = false;
            deluser = false;

            const s = "http://localhost:8080/users" 
     
            let res = await fetch(s, {
     
                method: "GET",
                headers : {
                    "Content-type": "application/json",
                    "Accept" : "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080"
                }
            })

            UserRows = await res.json()
                        console.log(UserRows);



            document.getElementById("myTable").deleteTFoot();
        }
     
        async function deletefooter2() {
            f1 = false;
            f2 = false;
            f3 = false;
            f4 = true;
            disp = false;
            disp1 = false;
            deluser = false;


            const s = "http://localhost:8080/workers" 
     
            let res = await fetch(s, {
     
                method: "GET",
                headers : {
                    "Content-type": "application/json",
                    "Accept" : "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080"
                }
            })

            WorkerRows = await res.json()

            document.getElementById("myTable1").deleteTFoot();
        }
     
        async function workerdata() {
            const body = {
                id: id,
                name: workername,
                space: space,
                slot: slot,
                hours: "0",
                rating: "0",
            };
            WorkerRows.push(body);
     
            const s = "http://localhost:8080/add_worker";
     
            const res = await fetch( s , {
     
                method : "POST",
                headers : {
                    "Content-type": "application/json",
                    "Accept" : "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080",
                },
     
                body : JSON.stringify(body),
            }
            );
     
            console.log(WorkerRows);
        }
     
        let f1 = false;
        let f2 = false;
        let f3 = false;
        let f4 = false;
        var status;
        function checking(radioid){
            status = document.getElementById(radioid).checked;
            const body = {
                id : id,
                status : status
            }
            status_array.push(body)
            console.log(status_array);
        }
        // checks whether the radio button is selected or not. If selected, returns true to status
        // status and id are put together in an object called body which is pushed onto the status_array
        // the status_array stores the status (true/false)
     
        const status_array = [
            {
                id: "459",
                status: false
            }
        ]
     
        let arr1 = [
            { name: "1" },
            { name: "2" },
            { name: "3" },
            { name: "4" },
            { name: "5" },
        ];
        let brr1 = [false, false, false];
     
        let arr2 = [{ name: "Slot 1" }, { name: "Slot 2" }, { name: "Slot 3" }];
        let brr2 = [false, false, false];
     
        let arr3 = [{ name: "Car 1" }, { name: "Car 2" }, { name: "Car 3" }];
        let brr3 = [false, false, false];
     
        let arr4 = [
            { name: "Worker 1" },
            { name: "Worker 2" },
            { name: "Worker 3" },
        ];
        let brr4 = [false, false, false];
     
        let id, workername, space, slot;
     
        let finalcheckid;
     
        async function whichcheck(){
            for(let i = 0; i < status_array.length; i++){
                if (status_array[i].status == true){
                    finalcheckid = status_array[i].id
                };
            }
     
            const s = "http://localhost:8080/remove_worker/" + finalcheckid;
     
            fetch(s, {
     
                method: "DELETE",
                headers : {
                    "Content-type": "application/json",
                    "Accept" : "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080"
                }
            })
            
            disp1 = true;
            disp = false;
            console.log(finalcheckid)
            
            
     
     
        }
        // whichcheck() checks which one is true and accordingly returns the key that has the status as true in finalcheckid
     
        
     
        async function del(id){
            let s = "http://localhost:8080/remove_worker/" + id;
     
            fetch(s, {
     
                method: "DELETE",
                headers : {
                    "Content-type": "application/json",
                    "Accept" : "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080"
                }
            })


        }


        async function delu(id){
            console.log(id)

            
            let s = "http://localhost:8080/remove_user/"+id;
     
            fetch(s, {
     
                method: "DELETE",
                headers : {
                    "Content-type": "application/json",
                    "Accept" : "application/json",
                    "Access-Control-Allow-Origin": "http://localhost:8080"
                }
            })

            
        }





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
                        <NavLink href="https://github.com/an-bhv/carpark"
                            >GitHub</NavLink
                        >
                    </NavItem>
                    <Dropdown nav inNavbar>
                        <DropdownToggle nav caret>Options</DropdownToggle>
                        <DropdownMenu end>
                            <DropdownItem>Profile</DropdownItem>
                            <DropdownItem>Log Out</DropdownItem>
                        </DropdownMenu>
                    </Dropdown>
                </Nav>
            </Collapse>
        </Navbar>
    </div>
     
    <br />
    <h1 style="color: #333"><b align="center">Welcome Admin!</b></h1>
    <br />
     
    <Container>
        <Row cols={4}>
            <Col>
                <Card
                    style="background-color:#333;color:lawngreen;border-color:#333;width:15rem;margin-right:5px"
                >
                    <button
                        class="one"
                        on:click={function () {
                            f1 = true;
                            f2 = false;
                            f3 = false;
                            f4 = false;
                            disp = false;
                            disp1 = false;
                            deluser = false;
                        }}>Manage Parking Spaces</button
                    >
                </Card>
            </Col>
            <br /><br />
     
            <Col>
                <Card
                    style="background-color:#333;color:lawngreen;border-color:#333;width:15rem;margin-right:5px"
                >
                    <button
                        class="one"
                        on:click={function () {
                            f1 = false;
                            f2 = true;
                            f3 = false;
                            f4 = false;
                            disp = false;
                            disp1 = false;
                            deluser = false;
                        }}>Manage Parking Slots</button
                    >
                </Card>
            </Col>
            <br /><br />
     
            <Col>
                <Card
                    style="background-color:#333;color:lawngreen;border-color:#333;width:15rem;margin-right:5px"
                >
                    <button class="one" on:click|preventDefault={deletefooter1}
                        >Manage Users</button
                    >
                </Card>
            </Col>
            <br /><br />
     
            <Col>
                <Card
                    style="background-color:#333;color:lawngreen;border-color:#333;width:15rem;margin-right:5px"
                >
                    <button class="one" on:click|preventDefault={deletefooter2}
                        >Manage Workers</button
                    >
                </Card>
            </Col>
        </Row>
    </Container>
    <br /><br />
     
    {#if f3 == true}
        <h4 align="center" style="color:#333 !important"><b>User Database</b></h4>
        <br />
        <div
            class="one"
            style="border-radius:5px !important; width: 60rem; align-self: center;"
        >
            <Table
                responsive
                rows={UserRows}
                let:row
                style="color:lawngreen;"
                id="myTable"
            >
                 
                <Column header="ID" width="8rem">
                    {row.id}
                </Column>
                <Column header="First Name" width="8rem">
                    {row.firstname}
                </Column>
                <Column header="Last Name" width="8rem">
                    {row.lastname}
                </Column>
                <Column header="Address" width="8rem">
                    {row.address}
                </Column>
                <Column header="Email">
                    {row.email}
                </Column>
                <Column header="Phone Number" width="8rem">
                    {row.number}
                </Column>
                <Column header="Car Registration Number" width="8rem">
                    {row.crn}
                </Column>
                <Column header="Balance" width="8rem">
                    {row.balance}
                </Column>
                <Column header="#" width="2rem">
                    <Button on:click={()=>delu(row.id)}>X</Button>
                    <!--  -->
                </Column>
            </Table>
        </div>
        <br />
        <button
            style="color:lawngreen;background-color:#333;border-color:#333;width:8rem; align-self:center;"
            on:click={function () {
                deluser = true;
            }}>Delete User</button
        >
    {/if}
     
    {#if f4 == true}
        <!-- fields: id, name, space, slot, hours worked, Rating, -->
        <h4 align="center" style="color:#333 !important"><b>Worker Database</b></h4>
        <br />
        <div
            class="one"
            style="border-radius:5px !important; width: 60rem; align-self: center;"
        >
            <Table
                responsive
                rows={WorkerRows}
                let:row
                style="color:lawngreen;"
                id="myTable1"
            >
                
                <Column header="ID" width="8rem">
                    {row.id}
                </Column>
                <Column header="Name" width="8rem">
                    {row.name}
                </Column>
                <Column header="Space" width="8rem">
                    {row.space}
                </Column>
                <Column header="slot" width="8rem">
                    {row.slot}
                </Column>
                <Column header="Hours Worked" width="8rem">
                    {row.hours_worked}
                </Column>
                <Column header="Rating" width="8rem">
                    {row.avg_rating}
                </Column>
                <Column header="#" width="2rem">
                    <Button on:click={()=>del(row.id)}>X</Button>
                    <!--  -->
                </Column>
            </Table>
        </div>
        <br />
        <button
            style="color:lawngreen;background-color:#333;border-color:#333;width:8rem; align-self:center;"
            on:click={function () {
                disp = true;
                disp1 = false;
            }}>Add Worker</button
        >
        <button
            style="color:lawngreen;background-color:#333;border-color:#333;width:8rem; align-self:center;"
            on:click|preventDefault={whichcheck}>Delete Worker</button
        >
        <!-- this is where whichcheck occurs -->
    {/if}
     
    {#if disp1 == true}
        <br />
        <form>
            <div
                class="box"
                style="background-color:#333;border-color:#333;border-radius:5px;width:400px;display:flex;padding:10px"
            >
                Enter ID: <Input
                    type="text"
                    value = {finalcheckid}              
                    style="width:14rem; align-self:center"
                />
                <!-- the value of finalcheckid should be the default value of this f -->
                <br />
                <button
                    style="align-self:center;width:8rem;align-text:center;color:#333;background-color:lawngreen;border-color:lawngreen"
                    >Delete</button
                >
            </div>
        </form>
        <br>
    {/if}
     
    {#if disp == true}
        <br />
        <form>
            <div
                class="box"
                style="background-color:#333;border-color:#333;border-radius:5px;width:400px;display:flex;padding:10px"
            >
                ID: <Input
                    type="text"
                    bind:value={id}
                    style="width:14rem; align-self:center"
                /><br />
                Name: <Input
                    type="text"
                    bind:value={workername}
                    style="width:14rem; align-self:center"
                /><br />
                Space: <Input
                    type="text"
                    bind:value={space}
                    style="width:14rem; align-self:center"
                /><br />
                Slot: <Input
                    type="text"
                    bind:value={slot}
                    style="width:14rem; align-self:center"
                /><br />
                <button
                    style="align-self:center;width:8rem;align-text:center;color:#333;background-color:lawngreen;border-color:lawngreen"
                    on:click|preventDefault={workerdata}>Add</button
                >
            </div>
        </form>
    {/if}
     
    {#if f2 == true}
        {#each arr2 as { name }, i}
            <div class="box">
                <Card style="background-color: #333">
                    <a
                        href="#"
                        on:click={function () {
                            brr2[i] = true;
                        }}><h3>{name}</h3></a
                    >
                    {#if brr2[i] == true}
                        <h4>10am - 12pm</h4>
                    {/if}
                </Card>
            </div>
            <br />
        {/each}
    {/if}
     
    {#if deluser == true}
        <br />
        <form>
            <div
                class="box"
                style="background-color:#333;border-color:#333;border-radius:5px;width:400px;display:flex;padding:10px"
            >
                Enter ID: <Input
                    type="number"
                    bind:value={id}
                    style="width:14rem; align-self:center"
                /><br />
                <button
                    style="align-self:center;width:8rem;align-text:center;color:#333;background-color:lawngreen;border-color:lawngreen"
                    >Delete</button
                >
            </div>
        </form>
        <br>
    {/if}
     
    {#if f1 == true}
        {#each arr3 as { name }, i}
            <div class="box">
                <a
                    href="#"
                    on:click={function () {
                        brr3[i] = true;
                    }}><h3>{name}</h3></a
                >
                {#if brr3[i] == true}
                    <h4>Owner Name: Pratyush Gupta</h4>
                    <h4>Owner Phone Number: 9045217756</h4>
                    <h4>Name of the Car: Vitara Brezza</h4>
                    <h4>Car Company: Maruti Suzuki</h4>
                    <h4>Car Number: DL 4C AA 1207</h4>
                    <h4>Car Colour: Silver</h4>
                    <h4>Car in-time: 10:47:55 am</h4>
                    <h4>Fees Paid: $2.5</h4>
                    <h4>Fees Due: $5.5</h4>
                {/if}
            </div>
        {/each}
    {/if}
     
    <style>
        .box {
            align-self: center;
            color: lawngreen;
            text-align: center;
        }
     
        button{
            text-align: center;
        }
        * {
            display: flex;
            flex-direction: column;
     
            justify-content: center;
        }
        .one {
            background-color: #333 !important;
            border-color: #333;
            color: lawngreen;
        }
        .searchbar-container {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            border-radius: 5px;
        }
     
        span {
            color: lawngreen;
            background-color: #212529;
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