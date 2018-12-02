////In Progress, Code Not Ready
////In Progress, Code Not Ready
////In Progress, Code Not Ready
////In Progress, Code Not Ready
////In Progress, Code Not Ready
////In Progress, Code Not Ready
////In Progress, Code Not Ready
////In Progress, Code Not Ready
////In Progress, Code Not Ready
////In Progress, Code Not Ready
////In Progress, Code Not Ready
#include <windows.h>

LPSTR WindowClass = "WindowClass";
MSG msg;

LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam);

int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow)
{

	// Structure Parameters
	WNDCLASSEX wc;

	wc.cbSize = sizeof(WNDCLASSEX);
	wc.style = 0;
	wc.lpfnWndProc = WndProc;
	wc.cbClsExtra = 0;
	wc.cbWndExtra = 0;
	wc.hInstance = hInstance;
	wc.hIcon = LoadIcon(NULL, IDI_APPLICATION);
	wc.hCursor = LoadCursor(NULL, IDC_ARROW);
	wc.hbrBackground = (HBRUSH)(COLOR_WINDOW + 1);
	wc.lpszMenuName = NULL;
	wc.lpszClassName = WindowClass;
	wc.hIconSm = LoadIcon(NULL, IDI_APPLICATION);

	//Is Registering Window Accomplished
if (!RegisterClassEx(&wc)) {
		MessageBox(NULL, "Window Registration Failed!", "Error!", MB_ICONEXCLAMATION | MB_OK);
		return 0;
	}
	
	
	// Creating Window
	HWND hwnd;
	HWND g_hButton; //button declaration
	HWND g_hCheckbox; //checkbox button declaration
	HWND g_hRadioButton; //radiobutton declaration

	hwnd = CreateWindowEx(WS_EX_CLIENTEDGE, WindowClass, "Window Exercise", WS_OVERLAPPEDWINDOW,
		CW_USEDEFAULT, /* x */
		CW_USEDEFAULT, /* y */
		200, /* width */
		280, /* height */
		NULL, NULL, hInstance, NULL);
		
	
		
		
	g_hButton = CreateWindowEx(0, "BUTTON", "My Button Text", WS_CHILD | WS_VISIBLE,
		15, 10, 150, 30, hwnd, NULL, hInstance, NULL);
	g_hCheckbox = CreateWindowEx(0, "BUTTON", "Checkbox", WS_CHILD | WS_VISIBLE | BS_CHECKBOX,
		15, 40, 150, 30, hwnd, NULL, hInstance, NULL);
	g_hRadioButton = CreateWindowEx(0, "BUTTON", "Radio Button", WS_CHILD | WS_VISIBLE | BS_RADIOBUTTON,
		15, 70, 150, 30, hwnd, NULL, hInstance, NULL);




	if (hwnd == NULL) {
		MessageBox(NULL, "Window Creation Failed!", "Error!", MB_ICONEXCLAMATION | MB_OK);
		return 0;
	}

	ShowWindow(hwnd, nCmdShow); // Show window...
	UpdateWindow(hwnd);

	//Message Loop
	while (GetMessage(&msg, NULL, 0, 0)) /* If no error is received... */
	{
		TranslateMessage(&msg); /* Translate key codes to chars if present */
		DispatchMessage(&msg);  /* Send it to WndProc */
	}
	return msg.wParam;
}

// Event Handling
LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam)
{
	switch (msg)
	{
		/*
	case WM_COMMAND:
	if ((HWND)lParam == g_hButton) //show msgbox if g_hButton is pressed
		MessageBox(hwnd, "You pressed it! Hurray!", "It works!", MB_ICONINFORMATION);
*/
	break;
		
	case WM_CLOSE:
		DestroyWindow(hwnd);
		break;

	case WM_DESTROY:
		PostQuitMessage(0);
		break;
		
		


	default:
		return DefWindowProc(hwnd, msg, wParam, lParam);
	}

	return 0;
}
