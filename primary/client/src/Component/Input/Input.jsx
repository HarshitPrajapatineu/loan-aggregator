import React from "react";

const Input = ({
  value,
  onChangeHandler,
  id = "",
  name = "",
  classes = "",
  placeholder = "",
  type = 'text',
  isrequired = false
}) => {
  return (
    <input
      id={id}
      name={name}
      type={type}
      value={value}
      onChange={onChangeHandler}
      className={`input ${classes}`}
      placeholder={placeholder}
      required= {isrequired}
    />
  );
};

export default Input;
