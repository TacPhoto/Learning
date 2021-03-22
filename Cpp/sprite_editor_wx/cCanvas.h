#pragma once

#include "wx/wx.h"
#include "wx/vscroll.h"

class cCanvas : public wxHVScrolledWindow
{
public:
	cCanvas(wxWindow *parent);
	~cCanvas();

public:
	int m_nPixelSize = 8;
	void SetSpriteData(int rows, int columns, unsigned char* pSprite);
	void SetPixelSize(int n);
	void SetColor(int c);

private:
	unsigned char* m_pSprite = nullptr;
	wxColour palette[16];
	int m_nColor = 0;

private:
	virtual wxCoord OnGetRowHeight(size_t row) const;
	virtual wxCoord OnGetColumnWidth(size_t col) const;

	void OnDraw(wxDC& dc);
	void OnPaint(wxPaintEvent& evt);

	void OnMouseLeftDown(wxMouseEvent& evt);

	wxDECLARE_EVENT_TABLE();
};

