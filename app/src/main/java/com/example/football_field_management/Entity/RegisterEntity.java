package com.example.football_field_management.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"Username","id_order"},foreignKeys = {
        @ForeignKey(entity = UserEntity.class,
                parentColumns = "Username",
                childColumns = "Username",
                onDelete = ForeignKey.CASCADE),

        @ForeignKey(entity = Order_PitchEntity.class,
                parentColumns = "id_order",
                childColumns = "id_order",
                onDelete = ForeignKey.CASCADE),
      },tableName = "register")
public class RegisterEntity {
      @NonNull
     String Username;
      @NonNull
     int id_order;

      public RegisterEntity(@NonNull String username, int id_order) {
            Username = username;
            this.id_order = id_order;
      }

      public RegisterEntity() {
      }

      @NonNull
      public String getUsername() {
            return Username;
      }

      public void setUsername(@NonNull String username) {
            Username = username;
      }

      public int getId_order() {
            return id_order;
      }

      public void setId_order(int id_order) {
            this.id_order = id_order;
      }
}
