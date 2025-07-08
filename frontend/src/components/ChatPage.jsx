import React, { useRef, useState } from "react";
import { MdAttachFile, MdSend } from "react-icons/md";

const [messages, setMessages] = useState([]);
const [input, setInput] = useState("");
const inputRef = useRef(null);
const chatBoxRef = useRef(null);
const [stompClient, setStompClient] = useState("");

const ChatPage = () => {
  return (
    <div className="">
      {/* this is a header */}
      <header className="dark:border-gray-700 h-20 fixed w-full dark:bg-gray-900 py-5 shadow flex justify-around items:center">
        {/* room name container  */}
        <div>
          <h1 className="text-xl font-semibold">
            Room : <span>Family Room</span>
          </h1>
        </div>

        {/* username container  */}
        <div>
          <h1 className="text-xl font-semibold">
            User : <span>Chetan</span>
          </h1>
        </div>

        {/* button : leave room  */}
        <div>
          <button className="dark:bg-red-500 dark:hover:bg-red-700 px-3 py-3 rounded-full">
            Leave Room
          </button>
        </div>
      </header>
      <main className="py-20 px-10   w-2/3 dark:bg-slate-600 mx-auto h-screen overflow-auto ">
        {messages.map((message, index) => {
          <div
            key={index}
            className={`flex ${
              message.sender === currentUser ? "justify-end" : "justify-start"
            } `}
          >
            <div
              className={`my-2 ${
                message.sender === currentUser ? "bg-green-800" : "bg-gray-800"
              } p-2 max-w-xs rounded`}
            >
              <div className="flex flex-row gap-2">
                <img
                  className="h-10 w-10"
                  src="https://avatar.iran.liara.run/public/boy"
                  alt=""
                />
                <div className="boder flex flex-col gap-1"></div>
                <div>
                  <p className="text-sm font-bold">{message.sender}</p>
                  <p>{message.content}</p>
                </div>
              </div>
            </div>
            ;
          </div>;
        })}
        <div className="message container"></div>
      </main>
      {/* input message container */}
      <div className="fixed bottom-4 w-full h-16">
        <div className="h-full pr-10 gap-4 flex items-center justify-between rounded-full w-1\2 mx-auto dark:bg-gray-900">
          <input
            type="text"
            placeholder="Type your message here..."
            className="w-full  dark:border-gray-600 b dark:bg-gray-800  px-5 py-2 rounded-full h-full focus:outline-none"
          />
          <div className="flex gap-1">
            <button className="dark:bg-purple-600 h-10 w-10 flex justify-center items-center rounded-full">
              <MdAttachFile />
            </button>
            <button className="dark:bg-green-600 h-10 w-10 flex justify-center items-center rounded-full">
              <MdSend />
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ChatPage;
