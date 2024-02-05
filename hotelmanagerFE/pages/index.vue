<template>
    <div>
        <div v-if="error">
            <p>{{ error }}</p>
        </div>
        <div v-else class="container mx-auto bg-darkBackground text-customGreen">
            <h1 class="text-center text-5xl text-white my-6">Hotel Room List</h1>
            <label>
                Show Only Free Rooms
                <input type="checkbox" v-model="showOnlyFreeRooms" />
            </label>
            <div class="flex flex-col items-center bg-slate-700">
                <div class="my-6">
                    <HotelRoomCard class="min-w-96" v-for="hotelRoom in filteredRooms" :key="hotelRoom.id"
                        :hotel-room="hotelRoom" :is-new-room="hotelRoom === newHotelRoom"
                        @update-hotel-room="updateHotelRoom" />
                </div>
                <button @click="addNewHotelRoom"
                    class="flex-1 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded justify-center my-6">
                    Add Room
                </button>
            </div>
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
            if (error.code === 'ECONNREFUSED')
                return { error: 'The server is currently down. Please try again later.' };
            return { rooms: [] };
        }
      },
      data() {
        return {
            error: null,
            rooms: [], // For the existing rooms
            hotelRoom: {},
            newHotelRoom: { roomSize: 'SINGLE', hasMinibar: false},
            editMode: false,
            showOnlyFreeRooms: false,
            editedRoom: {},
        };
    },
    computed: {
        filteredRooms(){
            if (this.showOnlyFreeRooms){
                return this.rooms.filter((room) => room.isFree );
            } else {
                return this.rooms;
            }
        }
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
        },
        async addNewHotelRoom() {
            try {
                const response = await this.$axios.post('/api/hotel/rooms', null, {
                    params: {
                    roomSize: this.newHotelRoom.roomSize,
                    hasMinibar: this.newHotelRoom.hasMinibar
                }}
                );
                const newRoom = response.data;
                this.rooms.push(newRoom);
                this.editMode = true;
                this.editedRoom = { ...newRoom };
            } catch (error){
                console.error('Error creating new Room:', error);
            }
        
        },
    },
  };
  </script>
  