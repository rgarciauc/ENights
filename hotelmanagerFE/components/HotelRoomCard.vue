<template>
  <nuxt-link :to="'/api/hotel/rooms/' + hotelRoom.id">
    <div class="card">
      <p>Room Size: {{ hotelRoom.roomSize }}</p>
      <p>Minibar: {{ hotelRoom.hasMinibar ? 'Yes' : 'No' }}</p>
      <p>Availability: {{ hotelRoom.isFree ? 'Free' : 'Occupied' }}</p>
      <p>Number: {{ hotelRoom.id }}</p>
      
    </div>
  </nuxt-link>
</template>
  
<script>
  export default {
    props: {
      hotelRoom: {
        type: Object,
        required: true,
      },
    },
  };
  </script>
  
<style scoped>
.card {
  border: 1px solid #ccc;
  padding: 10px;
  margin: 10px;
  border-radius: 8px;
}
</style>