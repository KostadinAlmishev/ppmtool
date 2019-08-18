import { GET_ERRORS } from "../actions/types";

const initialState = {
  // We dont have errors at the beggining

};

export default function(state = initialState, action) {
  switch(action.type) {
    case GET_ERRORS:
      return action.payload;


    default:
      return state;
  }
  
}