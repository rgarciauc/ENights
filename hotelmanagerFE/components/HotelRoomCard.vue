<template>
  <div>
    <div v-if="!editMode">
      <div class="card">
        <p>Room Size: {{ hotelRoom.roomSize }}</p>
        <p>Minibar: {{ hotelRoom.hasMinibar ? 'Yes' : 'No' }}</p>
        <p>Availability: {{ hotelRoom.isFree ? 'Free' : 'Occupied' }}</p>
        <p>Number: {{ hotelRoom.id }}</p>
      </div>
      <button @click.prevent="toggleEditMode" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Edit</button>
    </div>
    <div v-else class="card">
      <form @submit.prevent="saveChanges">
        <label for="roomSize">Room Size:</label>
        <input v-model="editedRoom.roomSize" class="bg-white focus:outline-none focus:shadow-outline border border-gray-300 rounded-lg py-2 px-4 block w-full appearance-none leading-normal"/>

        <label for="roomSize">Minibar:</label>
        <input v-model="editedRoom.hasMinibar" class="bg-white focus:outline-none focus:shadow-outline border border-gray-300 rounded-lg py-2 px-4 block w-full appearance-none leading-normal"/>

        <label for="roomSize">Availability:</label>
        <input v-model="editedRoom.isFree" class="bg-white focus:outline-none focus:shadow-outline border border-gray-300 rounded-lg py-2 px-4 block w-full appearance-none leading-normal"/>
        
        <button @click.prevent="toggleEditMode" class="bg-grey-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Cancel</button>
        <button type="submit" class="bg-green-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Save Changes</button>
      </form>
    </div>
  </div>
</template>
  
<script>
  export default {
    props: {
      hotelRoom: {
        type: Object,
        required: true,
      },
    },
    data() {
      return {
        editMode: false,
        editedRoom: { ...this.hotelRoom },
      };
    },
    methods: {
      toggleEditMode(){
        this.editMode = !this.editMode;
        if(!this.editMode){
          this.editedRoom = { ...this.hotelRoom };
        }
      },
      async saveChanges(){
        console.log("saving changes", this.editedRoom);
        try {
          // Make a request to update the room
          const response = await this.$axios.put(`/api/hotel/rooms/${this.hotelRoom.id}`, this.editedRoom);
          
          // Assuming the backend returns the updated room details
          this.$emit("update-hotel-room",{id: this.hotelRoom.id, updatedRoom: response.data});

          // Exit edit mode
          this.editMode = false;

          console.log('Room updated:', this.hotelRoom);
        } catch (error) {
            console.error('Error updating room:', error);
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
}
</style>