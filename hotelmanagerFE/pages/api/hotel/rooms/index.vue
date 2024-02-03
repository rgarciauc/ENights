<template>
  <div class="container mx-auto bg-darkBackground text-customGreen">
    <div class="flex flex-col items-center bg-slate-700">
      <h1 class="text-center">Hotel Room List</h1>
      <HotelRoomCard v-for="hotelRoom in hotels" :key="hotelRoom.id" :hotel-room="hotelRoom" />
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
        console.log('Fetching hotels...');
        const url = '/api/hotel/rooms';
        console.log('Absolute URL:', $axios.defaults.baseURL + url);
        const response = await $axios.get(url);
        console.log('Response:', response);
        return { hotels: response.data };
      } catch (error) {
        console.error('Error fetching hotels:', error);
        return { hotels: [] };
      }
    },
    data() {
      return {
        hotels: [],
      };
  },
};
</script>
