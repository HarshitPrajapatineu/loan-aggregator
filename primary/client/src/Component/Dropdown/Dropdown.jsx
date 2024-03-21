import "./Dropdown.css";
import React from "react";

const Dropdown = ({
  onChangeHandler,
  id = "",
  name = "",
  classes = "",
  options,
  value,
}) => {
  return (
    <>
    <select name={name} id={id} className={`input ${classes}`} value={value} onChange={onChangeHandler}>
            {options.map((el) => {return <option value={el.value}>{el.key}</option>  })}
          </select>
    </>
  );
};

export default Dropdown;
