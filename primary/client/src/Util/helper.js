export const getToken = () => {
  return localStorage.getItem('token');
}

export const setToken = (token) => {
  localStorage.setItem('token', token);
}

export const clearToken = () => {
  localStorage.removeItem('token');
}

export const setUser = (userId) => {
  localStorage.setItem('user', userId);
}

export const getUser = () => {
  return localStorage.getItem('user');
}

export const setRole = (roleId) => {
  localStorage.setItem('role', roleId);
}

export const getRole = () => {
  return localStorage.getItem('role');
}

export const getClient = () => {
  return localStorage.getItem('client');
}

export const setClient = (clientId) => {
  localStorage.setItem('client', clientId);
}