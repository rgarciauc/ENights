<template>
    <div class="container mx-auto bg-darkBackground text-customGreen">
        <div class="flex flex-col items-center bg-slate-700">
            <h1 class="text-center">Hotel Room List</h1>
            <HotelRoomCard v-for="hotelRoom in rooms" :key="hotelRoom.id" :hotel-room="hotelRoom"
                @update-hotel-room="updateHotelRoom" />
        </div>
    </div>
</template>
  
<script>
  import HotelRoomCard from '~/components/HotelRoomCard.vue';
  
  export default {
    components: {
      HotelRoomCard
    },
    async asyncData({ $axios }) {
        try {
          const url = '/api/hotel/rooms';
          const response = await $axios.get(url);
          console.log('Response:', response.data);
          return { rooms: response.data };
        } catch (error) {
          return { rooms: [] };
        }
      },
      data() {
        return {
          rooms: [],
          hotelRoom: {},
        };
    },
    methods: {
        updateHotelRoom({ id, updatedRoom }) {
    try {
      // Find the index of the room in the local data
      const index = this.rooms.findIndex(room => room.id === id);

      // If the room is found, update it locally
      if (index !== -1) {
        // Update the specific properties of the room
        this.$set(this.rooms, index, { ...this.rooms[index], ...updatedRoom });

        console.log('Room updated locally:', this.rooms[index]);
      } else {
        console.error('Room not found in local data:', id);
      }
    } catch (error) {
      console.error('Error updating room locally:', error);
    }
  }
    },
  };
  </script>
  