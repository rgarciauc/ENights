<template>
  <div>
    <div>
      <h1>Room Details</h1>
      <p>Room ID: {{ room.id }}</p>
      <p>Room Size: {{ room.roomSize }}</p>
      <p>Minibar: {{ room.hasMinibar ? 'Yes' : 'No' }}</p>
      <p>Availability: {{ room.isFree ? 'Free' : 'Occupied' }}</p>
    </div>
    <button @click="toggleEditMode">Edit room details</button>
    <div v-if="editMode">
      <label for="roomSize">Room Size:</label>
      <input id="roomSize" v-model="editedRoom.roomSize"  />
      <!-- Add other input fields for editing other attributes -->

      <button @click="saveChanges">Save Changes</button>
    </div>
  </div>
</template>
  
<script>
  export default {
    async asyncData({ params, $axios }) {
      try {
          const url = '/api/hotel/rooms/';
          const response = await $axios.get(url+`${params.id}`);
          console.log('Response:', response);
          return { room: response.data };
      } catch (error) {
        console.error('Error fetching hotel details:', error);
    return { error: 'Failed to fetch hotel details' };
      }
    },
    data(){
      return {
        editMode:false,
        editedRoom: { ...this.room },
      }
    },
    methods: {
      toggleEditMode() {
        this.editMode = !this.editMode;
      },
    },
    async saveChanges() {
        // Make a request to update the room
        try {
          const url = `/api/hotel/rooms/${this.room.id}`;
          const response = await this.$axios.put(url, this.editedRoom);
          console.log('Room updated:', response.data);

          // Update the original room data
          this.room = response.data;

          // Exit edit mode
          this.editMode = false;
        } catch (error) {
          console.error('Error updating room:', error);
        }
      },
};
  </script>