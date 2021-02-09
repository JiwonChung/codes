from evdev import UInput, ecodes as e
ui = UInput
ui.write(e.EV_ABS, e.ABS_WHEEL, 1)