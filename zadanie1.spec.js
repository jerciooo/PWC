describe('Trello API tests', () => {
  const baseUrl = 'https://api.trello.com/1';
  const apiKey = '<your-api-key>';
  const apiToken = '<your-api-token>';
  let boardId;

  it('should create a new board', () => {
    const boardName = 'My new board';
    const url = `${baseUrl}/boards?key=${apiKey}&token=${apiToken}&name=${boardName}`;

    cy.request({
      method: 'POST',
      url,
    }).then(response => {
      expect(response.status).to.eq(200);
      expect(response.body.name).to.eq(boardName);

      boardId = response.body.id;
    });
  });

  it('should delete the created board', () => {
    const url = `${baseUrl}/boards/${boardId}?key=${apiKey}&token=${apiToken}`;

    cy.request({
      method: 'DELETE',
      url,
    }).then(response => {
      expect(response.status).to.eq(200);
      expect(response.body._value).to.eq(null);
    });
  });
});