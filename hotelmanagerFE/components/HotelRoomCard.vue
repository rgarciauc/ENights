<template>
  <div>
    <div v-if="!editMode" class="flex">
      <div class="card flex-1">
        <p>Room Size: {{ hotelRoom.roomSize }}</p>
        <p>Minibar: {{ hotelRoom.hasMinibar ? 'Yes' : 'No' }}</p>
        <p>Availability: {{ hotelRoom.isFree ? 'Free' : 'Occupied' }}</p>
        <p>Number: {{ hotelRoom.id }}</p>
      </div>
      <button @click.prevent="toggleEditMode" class="text-white font-bold py-2 px-4 rounded"><svg aria-hidden="true" focusable="false" role="img" class="octicon octicon-pencil" viewBox="0 0 16 16" width="16" height="16" fill="currentColor" style="display: inline-block; user-select: none; vertical-align: text-bottom; overflow: visible;"><path d="M11.013 1.427a1.75 1.75 0 0 1 2.474 0l1.086 1.086a1.75 1.75 0 0 1 0 2.474l-8.61 8.61c-.21.21-.47.364-.756.445l-3.251.93a.75.75 0 0 1-.927-.928l.929-3.25c.081-.286.235-.547.445-.758l8.61-8.61Zm.176 4.823L9.75 4.81l-6.286 6.287a.253.253 0 0 0-.064.108l-.558 1.953 1.953-.558a.253.253 0 0 0 .108-.064Zm1.238-3.763a.25.25 0 0 0-.354 0L10.811 3.75l1.439 1.44 1.263-1.263a.25.25 0 0 0 0-.354Z"></path></svg></button>
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
      isNewRoom: {
        type: Boolean,
        default: false,
      },
    },
    data() {
      return {
        editMode: this.isNewRoom,
        editedRoom: { ...this.hotelRoom },
      };
    },
    watch: {
      hotelRoom: {
        immediate: true,
        handler(newVal) {
          this.editedRoom = { ...newVal };
        },
      },
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
          const response = await this.$axios.put(`/api/hotel/rooms/${this.hotelRoom.id}`, this.editedRoom);
          
          this.$emit("update-hotel-room",{
            id: this.hotelRoom.id, 
            updatedRoom: response.data
          });

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