import {combineReducers} from 'redux';
import headerReducer from './headerReducer';

const rootReducers = combineReducers({
  header: headerReducer,
})

export default rootReducers;
