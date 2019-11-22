import axios from 'axios';
import { GET_ERRORS, SET_CURRENT_USER } from './types';
import setJwtToken from '../../securityUtils/setJwtToken';
import jwt_decode from 'jwt-decode';

export const createNewUser = (newUser, history) => async dispatch => {
    try {
        await axios.post("/api/users/register", newUser);
        history.push("/login");
        dispatch({
            type: GET_ERRORS,
            payload: {}
        });
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        })
    }
}

export const login = loginRequest => async dispatch => {
    try {
        // post -> loginRequest
        const res = await axios.post("/api/users/login", loginRequest);

        // extract token from res.data
        const { token } = res.data;

        // store the token in local store
        localStorage.setItem("jwtToken", token);

        // set our token in header ***
        setJwtToken(token);

        // decode token on React
        const decoded = jwt_decode(token);

        // dispatch to our security Reducer
        dispatch({
            type: SET_CURRENT_USER,
            payload: decoded
        })

    } catch(err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        })
    }
}

export const logout = () => async dispatch => {
    localStorage.removeItem("jwtToken");
    setJwtToken(false);

    dispatch({
        type: SET_CURRENT_USER,
        payload: {}
    })
}