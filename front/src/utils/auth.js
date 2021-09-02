const setTokenInLocalStorage = (tokenInfo) => {
    console.log('SET LOCAL STORAGE')
    localStorage.setItem("auth_token",tokenInfo);
    console.log(localStorage.getItem('auth_token'))
};

const deleteTokenInLocalStorage = () => {
    localStorage.removeItem("auth_token");
};
export {deleteTokenInLocalStorage, setTokenInLocalStorage}