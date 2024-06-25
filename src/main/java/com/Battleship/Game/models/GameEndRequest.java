package com.Battleship.Game.models;

public class GameEndRequest {

        private long winnerId;

        private long loserId;

        public GameEndRequest() {
        }

        public GameEndRequest(long winnerId, long loserId) {
            this.winnerId = winnerId;
            this.loserId = loserId;
        }

        public long getLoserId() {
            return loserId;
        }

        public long getWinnerId() {
            return winnerId;
        }

        public void setLoserId(long loserId) {
            this.loserId = loserId;
        }

        public void setWinnerId(long winnerId) {
            this.winnerId = winnerId;
        }

}
