#include "cEditorFrame.h"

wxBEGIN_EVENT_TABLE(cEditorFrame, wxMDIChildFrame)
	EVT_SLIDER(20001, cEditorFrame::OnZoomChange)
wxEND_EVENT_TABLE()

cEditorFrame::cEditorFrame(wxMDIParentFrame* parent, wxString sName) : wxMDIChildFrame (parent, wxID_ANY, sName)
{
	m_Canvas = new cCanvas(this);

	m_StatusBar = this->CreateStatusBar(2, wxSTB_DEFAULT_STYLE, wxID_ANY);
	m_ZoomSlider = new wxSlider(m_StatusBar, 20001, 8, 1, 32);

}

cEditorFrame::~cEditorFrame()
{

}


void cEditorFrame::SetColor(int c)
{
	m_Canvas->SetColor(c);
}

bool cEditorFrame::Save(wxString sFileName)
{
	if (!sprBase.Load(sFileName.wc_str()))
		return false;
	else
	{
		for (int i = 0; i < sprBase.nWidth; i++)
			for (int j = 0; j < sprBase.nHeight; j++)
			{
				wchar_t glyph = sprBase.GetGlyph(i, j);
				short colour = sprBase.GetColour(i, j);

				if (glyph == L' ')
					m_pSprite[j * sprBase.nWidth + i] = 16;
				else
					m_pSprite[j * sprBase.nWidth + i] = colour & 0x000F;
			}
		m_Canvas->SetSpriteData(sprBase.nHeight, sprBase.nWidth, m_pSprite);
		return true;
	}
}

bool cEditorFrame::Open(wxString sFileName)
{
	m_sprBase = new olcSprite(sFileName.wc_str());

	if (m_sprBase->nWidth == 0 || m_sprBase->nHeight == 0)
		return false;
	else
	{
		m_pSprite = new unsigned char[m_sprBase->nWidth * m_sprBase->nHeight]{ 0 };

		for (int i = 0; i < m_sprBase->nWidth; i++)
			for (int j = 0; j < m_sprBase->nHeight; j++)
			{
				wchar_t glyph = m_sprBase->GetGlyph(i, j);
				short colour = m_sprBase->GetColour(i, j);

				if (glyph == L' ')
				{
					m_pSprite[j * m_sprBase->nWidth + i] = 16;
				}
				else
				{
					m_pSprite[j * m_sprBase->nWidth + i] = colour & 0x000F;
				}
			}

		m_Canvas->SetSpriteData(m_sprBase->nHeight, m_sprBase->nWidth, m_pSprite);
		return true;
	}
}

bool cEditorFrame::New(int r, int c)
{
	delete[] m_pSprite;
	m_pSprite = new unsigned char[r * c]{ 0 };
	m_Canvas->SetSpriteData(r, c, m_pSprite);
	sprBase = olcSprite(c, r);

	return false;
}

void cEditorFrame::OnZoomChange(wxCommandEvent& evt)
{
	m_StatusBar->SetStatusText(wxString("Zoom: ") << m_ZoomSlider->GetValue(), 1);
	m_Canvas->SetPixelSize(m_ZoomSlider->GetValue());
	evt.Skip();
}
