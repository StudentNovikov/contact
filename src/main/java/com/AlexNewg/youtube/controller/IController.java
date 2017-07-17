package com.AlexNewg.youtube.controller;

import com.AlexNewg.youtube.console.ui.State;

interface IController {
    void showMenu();

    State processInput(String input);
}
