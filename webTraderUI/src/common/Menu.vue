<template>
  <div class="menu">
    <table>
      <tbody >
        <tr>
          <td><router-link to="/makeOrder" id="router-li">New Order</router-link></td>
        </tr>
        <tr>
          <td><router-link to="/myOrders" id="router-li">My Orders</router-link></td>
         </tr>
        <tr>
          <td><router-link to="/marketDepth" id="router-li">Market Depth</router-link></td>
        </tr>
        <tr>
          <td><router-link to="/checkOrder" id="router-li">Check Order</router-link></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
}
</script>

<style scoped>
@import url(https://fonts.googleapis.com/css?family=Open+Sans:400,600);

*, *:before, *:after {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  background: #105469;
  font-family: 'Open Sans', sans-serif;
}
.menu{
  display: flex;
  justify-content: space-between;
  /* margin: 4em 2em; */
}
table {
  background: #012B39;
  border-radius: 1.5em;
  border-collapse: collapse;
  margin: 4em 2em;
  width: 300px;
  font-size: 1.25em;
  text-align: center;
  cursor: pointer;
}
th {
  border-bottom: 1px solid #364043;
  font-weight: 600;
  padding: 0.5em 1em;
}
td {
  color: #66FCF1;
  font-weight: 700;
  padding: 0.7em 1em;
}
tbody tr {
  transition: background 0.25s ease;
}
tbody tr:hover {
  background: #014055;
}
#router-li{
  text-decoration: none;
  color: #66FCF1;
}
</style>
