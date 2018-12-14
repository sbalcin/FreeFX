const initialState = {
  headerSignUpClicked: false
}

export default function headerReducer(state = initialState, action) {
    return {...state, headerSignUpClicked: false};
}
