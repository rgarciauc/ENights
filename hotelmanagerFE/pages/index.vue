<template>
    <div>
        <div v-if="error">
            <p>{{ error }}</p>
        </div>
        <div v-else class="container mx-auto bg-darkBackground text-customGreen">
            <h1 class="text-center text-5xl text-white my-6">Hotel Room List</h1>
            <div class="flex flex-row my-4">
                <div class="filter flex-1">
                    <label>
                        Show Only Free Rooms
                        <input type="checkbox" v-model="showOnlyFreeRooms" />
                    </label>
                </div>

                <div class="search flex-2">
                    <input v-model="searchTerm" placeholder="Search by Room ID" @input="handleInput" />
                    <button @click="searchRoom">Search</button>
                    <div v-if="selectedRoom">
                        <div class="card">
                            <button class="close-button" @click="closeCard">x</button>
                            <p>Room Size: {{ selectedRoom.roomSize }}</p>
                            <p>Minibar: {{ selectedRoom.hasMinibar ? 'Yes' : 'No' }}</p>
                            <p>Availability: {{ selectedRoom.isFree ? 'Free' : 'Occupied' }}</p>
                            <p>Number: {{ selectedRoom.id }}</p>
                        </div>
                    </div>
                    <div v-if="showNotFound" class="not-found-message">
                        {{ notFoundMessage }}
                    </div>
                </div>
            </div>

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
            searchTerm: '',
            selectedRoom: null,
            notFoundMessage: '',
            showNotFound: false,
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
        async searchRoom(){
            try {
                const roomNumber = parseInt(this.searchTerm);
                if (isNaN(roomNumber)) {
                    this.showNotFoundMessage("Please enter a valid room number.");
                    return;
                }
                const response = await this.$axios.get(`/api/hotel/rooms/${this.searchTerm}`);
                if(response.status === 200) {
                    this.selectedRoom = response.data;
                    console.log(response.data)
                }
            } catch (error) {
                if (error.response && error.response.status === 404) {
                    const errorMessage = error.response.data;
                    this.showNotFoundMessage(errorMessage || "not found!");
                } else if (error.request){
                    this.showNotFoundMessage("The server is currently down.");
                }
                else {
                    console.error('Error fetching hotel room:', error);
                }
            }
        },
        showNotFoundMessage(message){
            this.showNotFound = true;
            console.log(this.showNotFound);
            this.notFoundMessage = message;
            console.log(this.notFoundMessage)
            setTimeout(()=> {
                this.notFoundMessage = '';
                this.showNotFound = false;
            }, 2000);
        },
        closeCard() {
            this.selectedRoom = null;
            this.searchTerm = '';
        },
        handleInput() {
            if (!this.searchTerm) {
                this.closeCard();
                this.notFoundMessage = '';
            }
        },
    },
  };
  </script>
<style scoped>
.card {
    border: 1px solid #ccc;
    padding: 20px;
    margin: 10px;
    border-radius: 8px;
    position: relative;
}

.close-button {
    position: absolute;
    top: 10px;
    right: 10px;
    cursor: pointer;
}
</style>
  