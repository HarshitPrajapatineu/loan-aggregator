const Button = ({ onClickHandler, text, id = "", classes = "" }) => {
  return (
    <button id={id} onClick={onClickHandler} className={`button ${classes}`}>
      {text}
    </button>
  );
};

export default Button;
