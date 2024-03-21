import Button from "@mui/material/Button";
import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { useParams } from "react-router-dom";
import whatsapp from "../../Asset/whatsapp.png";

const detailsToDisplay = [
  { id: "stoneCode", label: "Stone code" },
  { id: "shape", label: "Shape" },
  { id: "size", label: "Size" },
  { id: "color", label: "Color" },
  { id: "clarity", label: "Clarity" },
];

const Stone = () => {
  const [stoneDetail, setStoneDetail] = useState();
  let { id } = useParams();
  const encodedUrl = encodeURIComponent(id || "");
  const videoRef = useRef<HTMLVideoElement>(null);
  const [playing, setPlaying] = useState(true);
  const [url, setUrl] = useState("");
  const [invert, setInvert] = useState(false);
  // const [controlClicked, setControlClicked] = useState({
  //   clicked: false,
  //   time: 0,
  // });

  useEffect(() => {
    const fetchInitialData = () => {
      axios.
      get(`htt://localhost:5000/quikloan/`).then((data) => {
        setStoneDetail(data.data);
        setUrl(data.data?.videoLink || "");
      });
    };
    fetchInitialData();
  }, [id, encodedUrl]);

  useEffect(() => {
    videoRef.current?.load();
  }, [url]);

  const makeStoneDetailRow = (itemToDisplay) => {
    if (!stoneDetail) {
      return <></>;
    }
    return (
      <div
        key={itemToDisplay.id}
        className="grid grid-flow-col justify-start gap-2 grid-cols-half items-center w-full pl-3"
      >
        <span className="flex justify-start">{itemToDisplay.label}</span>
        <span className="text-base overflow-auto text-ellipsis">
          <strong>
            {itemToDisplay.format
              ? itemToDisplay.format?.(stoneDetail[itemToDisplay.id])
              : stoneDetail[itemToDisplay.id]}
          </strong>
        </span>
      </div>
    );
  };

  // const handleDurationChange = (e: any) => {
  //   const videoNode = videoRef.current;
  //   if (!videoNode) {
  //     return;
  //   }
  //   if (!controlClicked.clicked) {
  //     return;
  //   }
  //   const currentTime = videoRef.current.currentTime;
  //   if (controlClicked.time === 0) {
  //     if (currentTime >= controlClicked.time + 1) {
  //       videoRef.current.currentTime = videoRef.current.duration - 1;
  //     }
  //   } else {
  //     if (currentTime >= controlClicked.time + 1)
  //       videoRef.current.currentTime = controlClicked.time - 1;
  //   }
  // };

  // useEffect(() => {
  //   const videoNode = videoRef.current;
  //   if (!videoNode) {
  //     return;
  //   }
  //   videoNode.addEventListener("timeupdate", handleDurationChange);
  //   return () => {
  //     videoNode.removeEventListener("timeupdate", handleDurationChange);
  //   };
  // }, [handleDurationChange]);

  const gotoTop = () => {
    if (videoRef.current?.currentTime) {
      videoRef.current.currentTime = 0;
    }
    // setControlClicked({
    //   time: 0,
    //   clicked: true,
    // });
  };

  const gotoBottom = () => {
    if (videoRef.current?.currentTime) {
      // setControlClicked({
      //   time: videoRef.current?.duration / 2,
      //   clicked: true,
      // });
      videoRef.current.currentTime = videoRef.current?.duration / 2;
    }
  };

  const gotoLeft = () => {
    if (videoRef.current?.currentTime) {
      // setControlClicked({
      //   time: videoRef.current?.duration / 4,
      //   clicked: true,
      // });
      videoRef.current.currentTime = videoRef.current?.duration / 4;
    }
  };

  const gotoRight = () => {
    if (videoRef.current?.currentTime || videoRef.current?.currentTime === 0) {
      // setControlClicked({
      //   time: (3 * videoRef.current?.duration) / 4,
      //   clicked: true,
      // });
      videoRef.current.currentTime = (3 * videoRef.current?.duration) / 4;
    }
  };

  const videoStyles = {
    filter: invert ? "invert(100%)" : "",
    // height: '500px',
    // width: '500px',
    // maxHeight: '',
    // 'maxWidth': '500px'
  };

  return (
    <div className="stone-details-wrapper flex-col lg:flex-row flex w-10/12 justify-evenly mx-auto gap-5 lg:my-36">
      <div className="video-container flex justify-center flex-col">
      </div>
      <div className="bg-white center w-full lg:w-6/12 flex flex-col justify-center items-center gap-2.5 lg:gap-4 rounded-md py-8">
        {makeStoneDetailRow(detailsToDisplay[0])}
        <div className="flex w-full lg:flex-col lg:gap-4">
          {makeStoneDetailRow(detailsToDisplay[1])}
          {makeStoneDetailRow(detailsToDisplay[2])}
        </div>
        <div className="flex w-full lg:flex-col lg:gap-4">
          {makeStoneDetailRow(detailsToDisplay[3])}
          {makeStoneDetailRow(detailsToDisplay[4])}
        </div>
        {makeStoneDetailRow(detailsToDisplay[5])}

        <Button
          variant="contained"
          color="success"
          className="mt-5"
          endIcon={<img src={whatsapp} style={{ width: 20, height: 20 }} alt="whatspp" />}
        >
          <a
            href={`https://wa.me/?text=${
              window.location.origin
            }/stone/sharable/${encodeURIComponent(encodedUrl)}`}
            data-action="share/whatsapp/share"
            target="_blank"
            rel="noopener noreferrer"
            className="no-underline text-inherit hover:text-inherit hover:no-underline"
          >
            Share to WhatsApp
          </a>
        </Button>
      </div>
    </div>
  );
};

export default Stone;
