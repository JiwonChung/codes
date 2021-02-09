import inputs
from inputs import get_gamepad


while 1:
    events = get_gamepad()
    for event in events:
        if event.ev_type == "Absolute":
            print(event.code + "'s value is ")
            print(event.state)
            print("------------------------------------")

